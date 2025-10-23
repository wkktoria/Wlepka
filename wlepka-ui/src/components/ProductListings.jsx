import { useMemo, useState } from "react";
import Dropdown from "./Dropdown";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";

const sortList = ["Popularność", "Cena od najniższej", "Cena od najwyższej"];

export default function ProductListings({ products }) {
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setSelectedSort] = useState(sortList[0]);

  const filteredAndSortedProducts = useMemo(() => {
    if (!Array.isArray(products)) {
      return [];
    }

    let filteredProducts = products.filter(
      (product) =>
        product.name.toLowerCase().includes(searchText.toLowerCase()) ||
        product.description.toLowerCase().includes(searchText.toLowerCase())
    );

    return filteredProducts.slice().sort((a, b) => {
      switch (selectedSort) {
        case sortList[2]:
          return parseFloat(b.price) - parseFloat(a.price);
        case sortList[1]:
          return parseFloat(a.price) - parseFloat(b.price);
        case sortList[0]:
        default:
          return parseInt(b.popularity) - parseInt(a.popularity);
      }
    });
  }, [products, searchText, selectedSort]);

  const handleSearchChange = (inputSearch) => {
    setSearchText(inputSearch);
  };

  const handleSortChange = (sortType) => {
    setSelectedSort(sortType);
  };

  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4 pt-12">
        <SearchBox
          label="Szukaj"
          placeholder="Szukaj naklejek..."
          value={searchText}
          handleSearch={(value) => handleSearchChange(value)}
        />
        <Dropdown
          label="Sortuj"
          options={sortList}
          value={sortList[0]}
          handleSort={(value) => handleSortChange(value)}
        />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {filteredAndSortedProducts.length > 0 ? (
          filteredAndSortedProducts.map((product) => (
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
