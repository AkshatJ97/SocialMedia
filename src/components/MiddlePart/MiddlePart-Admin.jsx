import { Avatar, Card } from '@mui/material'
import React from 'react'
import AddIcon from '@mui/icons-material/Add';
import StoryCircle from './StoryCircle';

const story=[11,1,1,1,1,1]
const MiddlePart = () => {
  return (
    <div className='px-20'>
      <div className='flex items-center p-5 rounded-b-md'>
        <Avatar 
        sx={{width:"5rem",height:"5rem"}}
        //src ='https://cdn.pixabay.com/photo/2024/03/03/20/44/cat-8611246_1280.jpg'
        className="flex flex-col items-center mr-4 cursor-pointer">
          <AddIcon sx={{fontSize:"3rem"}}/>
        </Avatar>
{story.map((item)=><StoryCircle/>)}
      </div>

    </div>
  )
}

export default MiddlePart