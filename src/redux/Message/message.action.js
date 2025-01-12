import { CREATE_CHAT_FAILURE, CREATE_CHAT_REQUEST, CREATE_CHAT_SUCCESS, CREATE_MESSAGE_FAILURE, CREATE_MESSAGE_REQUEST, CREATE_MESSAGE_SUCCESS, GET_ALL_CHATS_FAILURE, GET_ALL_CHATS_REQUEST, GET_ALL_CHATS_SUCCESS } from "./message.actionType"
import {api} from "../../config/api"
import { type } from "@testing-library/user-event/dist/type";

export const createMessage=(reqData)=>async(dispatch)=>{
    dispatch({type:CREATE_MESSAGE_REQUEST})
    try {
        console.log("Logging reqdata in message.action: ",reqData);
        
        const {data} = await api.post(`/api/message/chat/${reqData.chatId}`,reqData);
        reqData.sendMessageToServer(data)
        
        dispatch({type:CREATE_MESSAGE_SUCCESS,payload:data})
    } catch (error) {
        console.log("Catch error while creating a message",error);
        
        dispatch({type:CREATE_MESSAGE_FAILURE,payload:error})
    }
}

export const createChat=(chat)=>async(dispatch)=>{
    dispatch({type:CREATE_CHAT_REQUEST})
    try {
        const {data} = await api.post(`/api/chats`,chat);
        console.log("Created chats: ",chat);
        
        dispatch({type:CREATE_CHAT_SUCCESS,payload:data})
    } catch (error) {
        console.log("Catch error while creating a chat",error);
        
        dispatch({type:CREATE_CHAT_FAILURE,payload:error})
    }
}

export const getAllChats=()=>async(dispatch)=>{
    dispatch({type:GET_ALL_CHATS_REQUEST})
    try {
        const {data} = await api.get(`/api/chats`);
        
        dispatch({type:GET_ALL_CHATS_SUCCESS,payload:data})
    } catch (error) {
        console.log("Catch error while creating a message",error);
        
        dispatch({type:GET_ALL_CHATS_FAILURE,payload:error})
    }
}