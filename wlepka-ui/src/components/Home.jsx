import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import apiClient from "../api/apiClient";
import { useEffect, useState } from "react";

export default function Home() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    const response = await apiClient.get("/products");
    setProducts(response.data);
  };

  return (
    <div className="max-w-[1152px] mx-auto px-6 py-8">
      <PageHeading title="Poznaj nasze wlepki">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quo, quasi
        distinctio repellat eligendi quam ducimus repudiandae ullam nostrum
        omnis nihil quos fuga!
      </PageHeading>
      <ProductListings products={products} />
    </div>
  );
}
