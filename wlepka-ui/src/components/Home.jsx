import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import apiClient from "../api/apiClient";
import { useEffect, useState } from "react";

export default function Home() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const response = await apiClient.get("/products");
      setProducts(response.data);
    } catch (error) {
      setError(
        error.response?.data?.message ||
          "Nie udało się załadować produktów. Spróbuj ponownie."
      );
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <span className="text-xl font-semibold">Ładowanie produktów...</span>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <span className="text-xl text-red-500">Wystąpił błąd: {error}</span>
      </div>
    );
  }

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
