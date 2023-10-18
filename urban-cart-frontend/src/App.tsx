import "./App.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import SellItem from "./components/SellItem";
import { Home } from "./pages/Home";

function About() {
  return <h2>About</h2>;
}

function Error() {
  return <h2>404</h2>;
}
const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/about",
    element: <About />,
  },
  {
    path: "/sell-item",
    element: <SellItem />,
  },
  {
    path: "*",
    element: <Error />,
  },
]);
const App = () => {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
};

export default App;
