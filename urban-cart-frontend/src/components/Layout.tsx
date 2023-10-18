import React from "react";
import { NavBar } from "./NavBar";
import Footer from "./Footer";
import SideBar from "./SideBar";

export const Layout = ({ children }) => {
  return (
    <>
      <NavBar />
      <SideBar />
      <main>{children}</main>
      <Footer />
    </>
  );
};
