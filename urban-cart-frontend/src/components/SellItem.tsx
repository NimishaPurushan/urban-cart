import { Layout } from "./Layout";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";

const SellItem = () => {
  return (
    <Layout>
      <div>
        <h1>Sell Item</h1>
        <Box
          component="form"
          sx={{
            "& .MuiTextField-root": { m: 1, width: "25ch" },
          }}
          noValidate
          autoComplete="off"
        >
          <div>
            <TextField
              required
              id="outlined-required"
              label="Item Name"
              defaultValue="Item"
            />
            <TextField
              disabled
              id="outlined-price"
              label="Price"
              type="item-price"
              defaultValue="0"
            />
            <TextField
              id="outlined-quantity"
              label="quantity"
              type="number"
              defaultValue="0"
            />
            <TextField
              id="outlined-multiline-static"
              label="description"
              multiline
              rows={4}
              defaultValue="Default Value"
            />
          </div>
        </Box>
      </div>
    </Layout>
  );
};

export default SellItem;
