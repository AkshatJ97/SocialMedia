import { Avatar } from '@mui/material'
import React from 'react'

const StoryCircle = () => {
  return (
    <div>
        <div className="flex flex-col items-center mr-4 cursor-pointer">
          <Avatar
            sx={{ width: "5rem", height: "5rem" }}
            src="https://cdn.pixabay.com/photo/2024/10/30/13/53/woman-9161772_640.jpg"
          >
          </Avatar>
          <p>Social</p>
        </div>
    </div>
  )
}

export default StoryCircle
