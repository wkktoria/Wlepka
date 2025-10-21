import { useState } from "react";
import Dropdown from "./Dropdown";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";

const sortList = ["Popularność", "Cena od najniższej", "Cena od najwyższej"];

export default function ProductListings({ products }) {
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setSelectedSort] = useState(sortList[0]);

  let filteredAndSortedProducts = Array.isArray(products)
    ? products.filter(
        (product) =>
          product.name.toLowerCase().includes(searchText.toLowerCase()) ||
          product.description.toLowerCase().includes(searchText.toLowerCase())
      )
    : [];

  switch (selectedSort) {
    case sortList[2]:
      filteredAndSortedProducts = filteredAndSortedProducts.sort(
        (a, b) => parseFloat(b.price) - parseFloat(a.price)
      );
      break;
    case sortList[1]:
      filteredAndSortedProducts = filteredAndSortedProducts.sort(
        (a, b) => parseFloat(a.price) - parseFloat(b.price)
      );
      break;
    case sortList[0]:
    default:
      filteredAndSortedProducts = filteredAndSortedProducts.sort(
        (a, b) => parseInt(b.popularity) - parseInt(a.popularity)
      );
      break;
  }

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
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gapy-8 gap-x-6 py-12">
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
