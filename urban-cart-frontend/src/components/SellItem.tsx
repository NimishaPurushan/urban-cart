import { Layout } from "./Layout";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { useState } from "react";
import { postProduct } from "../utils/api";

const SellItem = () => {
  const [formData, setFormData] = useState({
    name: "Item",
    price: 0,
    quantity: 0,
    description: "Default Value",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    console.log("values:", name, value);
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
    console.log("formdata:", formData);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      console.log(formData);
      await postProduct(formData);
    } catch (error) {
      console.error("Error posting product:", error);
    }
  };

  return (
    <Layout>
      <div>
        <h1>Sell Item</h1>
        <form onSubmit={handleSubmit}>
          <Box
            component="div"
            sx={{
              "& .MuiTextField-root": { m: 1, width: "25ch" },
            }}
          >
            <div>
              <TextField
                required
                id="outlined-required"
                label="Item name"
                name="name"
                value={formData.name}
                onChange={handleChange}
              />
              <TextField
                required
                id="outlined-price"
                label="price"
                type="number"
                name="price"
                value={formData.price}
                onChange={handleChange}
              />
              <TextField
                required
                id="outlined-quantity"
                label="quantity"
                type="number"
                name="quantity"
                value={formData.quantity}
                onChange={handleChange}
              />
              <TextField
                id="outlined-multiline-static"
                label="description"
                name="description"
                multiline
                rows={4}
                value={formData.description}
                onChange={handleChange}
              />
            </div>
            <button type="submit">Sell</button>
          </Box>
        </form>
      </div>
    </Layout>
  );
};

export default SellItem;
