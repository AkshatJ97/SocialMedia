import { Avatar, Card, CardHeader } from "@mui/material";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { searchUser } from "../../redux/Auth/auth.action";
import { createChat } from "../../redux/Message/message.action";

export const SearchUser = () => {
  const [username, setUsername] = useState(null);
  const dispatch = useDispatch();
  const {message,auth} = useSelector(store => store)

  const handleSearchUser = (e) => {
    setUsername(e.target.value);
    console.log("Search User...");
    dispatch(searchUser(username));
  };
  const handleClick = (id) => {
    dispatch(createChat({userId:id}))
  };
  return (
    <div>
      <div className="py-5 relative">
        <input
          type="text"
          className="bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full"
          placeholder="Type Name..."
          onChange={handleSearchUser}
        />
        {username && (
          auth.searchUser.map((item)=><Card key ={item.id} className="absolute w-full z-10 top-[4.5rem] cursor-pointer">
          <CardHeader
            onClick={() => {
              handleClick(item.id);
              setUsername("");
            }}
            avatar={
              <Avatar src="https://cdn.pixabay.com/photo/2022/12/11/13/21/baby-7648901_640.jpg" />
            }
            title={item.firstName+ " "+item.lastName}
            subheader={item.firstName.toLowerCase()+ " "+item.lastName.toLowerCase()}
          />
        </Card>)
        )}
      </div>
    </div>
  );
};

export default SearchUser;
