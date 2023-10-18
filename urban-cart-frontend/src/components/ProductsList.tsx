import { useEffect, useState } from "react";
import ProductCard from "./ProductCard";
import { getProducts } from "../utils/api";

export const ProductsList = () => {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const ProductsPerPage = 12;

  useEffect(() => {
    // Calculate the offset based on the current page and number of products per page

    getProducts(page, ProductsPerPage).then((data) => {
      setProducts(data.content);
      setTotalPages(data.totalPages);
    });
  }, [page]);

  const containerStyle = {
    display: "flex",
    justifyContent: "left",
    flexWrap: "wrap",
    width: "100%",
    height: "100vh",
    padding: "10px",
    left: 200,
    // Adjust as needed
    // Add other CSS styles as needed
  };

  // Calculate the total number of pages

  const handlePageChange = (newPage) => {
    setPage(newPage);
  };

  return (
    <div>
      <div style={containerStyle}>
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>

      {/* Pagination controls */}
      <div>
        <button
          onClick={() => handlePageChange(page - 1)}
          disabled={page === 0}
        >
          Previous
        </button>
        <span>
          Page {page + 1} of {totalPages}
        </span>
        <button
          onClick={() => handlePageChange(page + 1)}
          disabled={page === totalPages - 1}
        >
          Next
        </button>
      </div>
    </div>
  );
};
