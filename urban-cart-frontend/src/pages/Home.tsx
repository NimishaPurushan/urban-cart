import { ProductsList } from "../components/ProductsList";
import { Layout } from "../components/Layout";

export const Home = () => {
  return (
    <Layout>
      <div>
        <ProductsList />
      </div>
    </Layout>
  );
};
