import ProductCard from "./ProductCard";

export default function ProductListings({ products }) {
  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gapy-8 gap-x-6 py-12">
        {products.length > 0 ? (
          products.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="text-center font-primary font-bold text-lg text-primary">
            Brak wlepek.
          </p>
        )}
      </div>
    </div>
  );
}
