import products from "../data/products";
import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";

export default function Home() {
  return (
    <div className="home-container">
      <PageHeading title="Poznaj nasze wlepki">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quo, quasi
        distinctio repellat eligendi quam ducimus repudiandae ullam nostrum
        omnis nihil quos fuga!
      </PageHeading>
      <ProductListings products={products} />
    </div>
  );
}
