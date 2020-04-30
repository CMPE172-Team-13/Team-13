import React, { useState } from "react";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import MenuIcon from '@material-ui/icons/Menu';
import { useHistory } from "react-router-dom";

const HeaderMenu = () => {
  const [anchorEl, setAnchorEl] = useState(null);

  let history = useHistory();

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <div style={{position:"absolute", right:"0", zIndex:"1"}}>
      <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
        <MenuIcon/>
      </Button>
      <Menu
        id="simple-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <MenuItem 
          onClick={() => {
            handleClose();
            history.push('/');
          }}>
            Donate
        </MenuItem>
        <MenuItem 
          onClick={() => {
            handleClose();
            history.push('/list');
          }}>
            Analytics
        </MenuItem>
      </Menu>
    </div>
  );
}

export default HeaderMenu; 