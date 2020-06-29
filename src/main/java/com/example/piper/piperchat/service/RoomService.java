package com.example.piper.piperchat.service;

import com.example.piper.piperchat.DTO.RoomDTO;
import com.example.piper.piperchat.DTO.UserDTO;
import com.example.piper.piperchat.incoming_data.NewMessage;
import com.example.piper.piperchat.model.*;
import com.example.piper.piperchat.outgoing_data.PrivateRoomDetails;
import com.example.piper.piperchat.repository.MessageRepository;
import com.example.piper.piperchat.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserService userService;
    @Autowired
    MessageRepository messageRepository;

    public boolean checkRoomDuplicity(String name){
        Room room = roomRepository.findByName(name);
        if(room == null){
            return false;
        }
        return true;
    }

    /**
     * Returns an array of null properties of an object
     * @param source
     * @return
     */
    public String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set emptyNames = new HashSet();
        for(java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }

    public RoomDTO createARoom(RoomDTO roomDTO){
        List<String> members = roomDTO.getParticipants();
        PrivateRoom privateRoom = new PrivateRoom();
        privateRoom.setIsARequest(true);
        BeanUtils.copyProperties(roomDTO, privateRoom, getNullPropertyNames(roomDTO));
        return addUsersToRooms(members, privateRoom);
    }

    public RoomDTO addUsersToRooms(List<String> participants, Room room) {
        participants.forEach(member -> {
            UserDTO user = userService.findUser(member);
            if(user != null){
                User userEntity = new User();
                BeanUtils.copyProperties(user, userEntity);
                room.addMember(userEntity);
            }
        });

//        if(room.getRoomType() == RoomType.PUBLIC){
//            PublicRoom savedRoom =
//        }
        Room savedRoom = roomRepository.save(room);
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(savedRoom, roomDTO);
        return roomDTO;
    }

    public RoomDTO createAGroup(RoomDTO roomDTO){
        List<String> members = roomDTO.getParticipants();
        PublicRoom publicRoom = new PublicRoom();
        BeanUtils.copyProperties(roomDTO, publicRoom);
        return addUsersToRooms(members, publicRoom);
    }

    public List<Message> getMessages(Long roomId){
        Room room = roomRepository.findByRoomId(roomId);
        List<Message> messages = room.getMessages();
        return messages;
    }

    @Transactional
    public Message addMessage(NewMessage newMessage){
        Message message = new Message();
        BeanUtils.copyProperties(newMessage, message);
        Room room = roomRepository.findByRoomId(newMessage.getRoomId());
        room.addMessage(message);
        roomRepository.saveAndFlush(room);
        return message;
    }

    public RoomDTO acceptChatRequest(RoomDTO roomDto) {
        PrivateRoom room = (PrivateRoom) roomRepository.findByRoomId(roomDto.getRoomId());
        room.setIsARequest(false);
        PrivateRoom savedRoom = roomRepository.save(room);
        BeanUtils.copyProperties(savedRoom, roomDto);
        return roomDto;
    }

    public void denyChatRequest(RoomDTO roomDto) {
        PrivateRoom room = new PrivateRoom();
        BeanUtils.copyProperties(roomDto, room);
        roomRepository.delete(room);
    }
}
