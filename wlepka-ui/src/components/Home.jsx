import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import apiClient from "../api/apiClient";
import { useLoaderData } from "react-router-dom";

export default function Home() {
  const products = useLoaderData();

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

export async function productsLoader() {
  try {
    const response = await apiClient.get("/products");
    return response.data;
  } catch (error) {
    throw new Response(
      error.message || "Nie udało się załadować produktów. Spróbuj ponownie.",
      {
        status: error.status || 500,
      }
    );
  }
}
