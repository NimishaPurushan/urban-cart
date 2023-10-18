import { config } from "./config";
import axios from "axios";

async function getProducts(offset, ProductsPerPage) {
  const apiUrl = config.baseURL + "products";
  console.log("apiUrl:", apiUrl);

  try {
    const response = await axios.get(apiUrl, {
      params: {
        size: ProductsPerPage,
        page: offset,
      },
    });
    console.log("response.data:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
}

async function getCategories(offset, ProductsPerPage) {
  const apiUrl = config.baseURL + "products";
  console.log("apiUrl:", apiUrl);

  try {
    const response = await axios.get(apiUrl, {
      params: {
        size: ProductsPerPage,
        page: offset,
      },
    });
    console.log("response.data:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
}

export { getProducts, getCategories };
