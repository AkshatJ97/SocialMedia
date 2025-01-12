import { Avatar, Card, CardHeader, IconButton } from "@mui/material";
import React from "react";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { useSelector } from "react-redux";

export const UserChatCard = ({ chat }) => {
    const {message,auth} = useSelector(store=>store)
    console.log("Logging auth",auth)
    console.log("Logging chat props",chat)

  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar
            sx={{
              width: "3.5rem",
              height: "3.5rem",
              fontSize: "1.5rem",
              bgcolor: "#191c29",
              color: "rgb(88,199,250)",
            }}
            src="https://cdn.pixabay.com/photo/2021/06/15/16/11/man-6339003_640.jpg"
          />
        }
        action={
          <IconButton>
            <MoreHorizIcon />
          </IconButton>
        }
        title={auth.user.id===chat.user[0].id?
            chat.user[1].firstName+ " "+chat.user[1].lastName :
            chat.user[0].firstName+" "+chat.user[0].lastName
            }
        subheader={"New Message"}
      ></CardHeader>
    </Card>
  );
};

export default UserChatCard;
