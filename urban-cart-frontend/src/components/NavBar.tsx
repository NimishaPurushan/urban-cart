import React from "react";
import Button from "@mui/material/Button";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Slide from "@mui/material/Slide";
import { Link } from "react-router-dom";

export const NavBar = () => {
  const navbarStyle = {
    position: "fixed",
    top: 0,
    right: 0,
    zIndex: 1000, // You can adjust this value as needed
  };

  return (
    <Slide direction="down" in={true} mountOnEnter unmountOnExit>
      <AppBar position="static" style={navbarStyle}>
        <Toolbar>
          <Typography
            variant="h6"
            component="div"
            style={{ left: 0 }}
            sx={{ flexGrow: 1 }}
          >
            Urban Cart
          </Typography>
          <Button color="inherit">
            <Link to="/">Home</Link>
          </Button>
          <Button color="inherit">
            <Link to="/sell-item">Sell Item</Link>
          </Button>
          <Button color="inherit">Cart</Button>
          <Button color="inherit">Login</Button>
        </Toolbar>
      </AppBar>
    </Slide>
  );
};
