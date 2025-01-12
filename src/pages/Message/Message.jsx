import {
  Avatar,
  Backdrop,
  CircularProgress,
  Grid,
  IconButton,
} from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import WestIcon from "@mui/icons-material/West";
import CallIcon from "@mui/icons-material/Call";
import VideoCallIcon from "@mui/icons-material/VideoCall";
import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import SearchUser from "../../components/SearchUser/SearchUser";
import "./Message.css";
import UserChatCard from "./UserChatCard";
import ChatMessage from "./ChatMessage";
import { useDispatch, useSelector } from "react-redux";
import { createMessage, getAllChats } from "../../redux/Message/message.action";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import { uploadToCloudinary } from "../../utils/uploadToCloudniry";
import SockJS from "sockjs-client";
import Stom from "stompjs";

const Message = () => {
  const dispatch = useDispatch();
  const { message, auth } = useSelector((store) => store);
  const [currentChat, setCurrentChat] = useState();
  const [messages, setMessages] = useState([]);
  const [selectedImage, setSelectedImage] = useState();
  const [loading, setLoading] = useState(false);
  const chatContainerRef = useRef(null);

  useEffect(() => {
    dispatch(getAllChats());
  }, []);

  const handleSelectImage = async (event) => {
    setLoading(true);
    console.log("handle select Image");
    const imageUrl = await uploadToCloudinary(event.target.files[0], "image");
    setSelectedImage(imageUrl);
    setLoading(false);
  };

  const handleCreateMessage = (value) => {
    const message = {
      chatId: currentChat?.chatId, // Safely access `id`
      content: value,
      image: selectedImage,
      sendMessageToServer
    };
    console.log("Logging message: ",message)
    console.log("Logging sendMessageToServer: ",sendMessageToServer);
    
    dispatch(createMessage(message));
  };

  useEffect(() => {
    setMessages((prevMessages) => [...prevMessages, message.message]);
  }, [message.message]);  
  
  const [stompClient,setStompClient] = useState(null);

  useEffect(()=>{
    const sock = new SockJS("http://localhost:8080/ws")
    const stomp = Stom.over(sock);
    setStompClient(stomp);
    stomp.connect({},onConnect,onErr)
  },[])

  const onConnect =()=>{
    console.log("WebSocket connected");
    
  }

  const onErr=(error)=>{
    console.log("Error",error);
    
  }
  useEffect(()=>{
    if(stompClient && auth.user && currentChat){
      const subscription = stompClient.subscribe(`/user/${currentChat.id}/private`,
        onMessageReceive
      )
    }
  })

  const sendMessageToServer = (newMessage) => {
    if (stompClient && newMessage) {
      const chatId = currentChat?.chatId?.toString();
      if (!chatId) {
        console.error("Chat ID is undefined. Cannot send message.");
        return;
      }
  
      stompClient.send(
        `/app/chat/${chatId}`,
        {},
        JSON.stringify(newMessage)
      );
    } else {
      console.error("WebSocket client or message is missing.");
    }
  };
  

  const onMessageReceive=(payload)=>{
    console.log("Message received from web socket",payload);
    const receivedMessage = JSON.parse(payload.body)
    console.log("Received Message",receivedMessage);
    setMessages([...messages,receivedMessage])    
  }

  useEffect(()=>{
    if(chatContainerRef.current){
      chatContainerRef.current.scrollTop = <chatContainerRef className="current scrollHeight">;</chatContainerRef>
    }
  },[messages])
  return (
    <div>
      <Grid container className="h-screen overflow-y-hidden">
        <Grid item xs={3} className="px-5">
          <div className="flex h-full justify-between space-x-2">
            <div className="w-full">
              <div className="flex space-x-4 items-center py-5">
                <WestIcon />
                <h1 className="text-xl font-bold">Home</h1>
              </div>
              <div className="h-[83vh]">
                <div className="">
                  <SearchUser />
                </div>
                <div className="h-full space-y-4 mt-5 overflow-y-scroll hideScrollbar">
                  {message.chats.map((item) => {
                    return (
                      <div
                        onClick={() => {
                          setCurrentChat(item);
                          setMessages(item.messages);
                        }}
                      >
                        <UserChatCard chat={item} />
                      </div>
                    );
                  })}
                </div>
              </div>
            </div>
          </div>
        </Grid>
        <Grid item xs={9} className="h-full">
          {currentChat ? (
            <div>
              <div className="flex justify-between items-center border-l p-5">
                <div className="flex items-center space-x-3">
                  <Avatar
                    src="https://cdn.pixabay.com/photo/2022/12/11/13/21/baby-7648901_640.jpg"
                    alt="profile_picture"
                  />
                  <p>
                    {auth.user?.id === currentChat.user[0]?.id
                      ? currentChat.user[1].firstName +
                        " " +
                        currentChat.user[1].lastName
                      : currentChat.user[0].firstName +
                        " " +
                        currentChat.user[0].lastName}
                  </p>
                </div>
                <div className="flex space-x-3 ">
                  <IconButton>
                    <CallIcon />
                  </IconButton>
                  <IconButton>
                    <VideoCallIcon />
                  </IconButton>
                </div>
              </div>
              <div ref={chatContainerRef} className="hideScrollbar overflow-y-scroll h-[82vh] px-2 space-y-5 py-5">
                {messages.map((item) => (
                  <ChatMessage item={item} />
                ))}
              </div>
              <div className="sticky bottom-0 border-l">
                {selectedImage && (
                  <img
                    src={selectedImage}
                    alt=""
                    className="w-[5rem] h-[5rem] object-cover px-2"
                  />
                )}

                <div className="py-5 flex items-center justify-center space-x-5">
                  <input
                    onKeyPress={(e) => {
                      if (e.key === "Enter" && e.target.value) {
                        handleCreateMessage(e.target.value);
                        setSelectedImage("")
                        e.target.value=""
                      }
                    }}
                    className="bg-transparent border border-[#3b40544] rounded-full w-[90%] py-3 px-5"
                    type="text"
                    placeholder="Write your Message here"
                  />
                  <div>
                    <input
                      type="file"
                      accept="image/*"
                      onChange={handleSelectImage}
                      className="hidden"
                      id="image-input"
                    />
                    <label htmlFor="image-input">
                      <AddPhotoAlternateIcon />
                    </label>
                  </div>
                </div>
              </div>
            </div>
          ) : (
            <div className="h-full space-y-5 flex flex-col justify-center items-center">
              <ChatBubbleOutlineIcon sx={{ fontSize: "15rem" }} />
              <p className="text-xl font-semibold">No Chat Selected</p>
            </div>
          )}
        </Grid>
      </Grid>
      <Backdrop
        sx={(theme) => ({ color: "#fff", zIndex: theme.zIndex.drawer + 1 })}
        open={loading}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  );
};

export default Message;
