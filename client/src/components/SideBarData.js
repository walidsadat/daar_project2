import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';

export const SidebarData = [

  {
    title: 'Home',
    path: '/',
    icon: <AiIcons.AiOutlineHome/>,
    cName: 'nav-text'
  },
  {
    title: 'Add a cv',
    path: '/addCv',
    icon: <AiIcons.AiOutlinePlusCircle />,
    cName: 'nav-text'
  },
  {
    title: 'Search a cv',
    path: '/searchcv',
    icon: <IoIcons.IoMdSearch />,
    cName: 'nav-text'
  },
];