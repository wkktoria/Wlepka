import toxicImage from "../assets/stickers/toxic.jpg";
import angelsImage from "../assets/stickers/angels.jpg";
import jeepImage from "../assets/stickers/jeep.jpg";

const products = [
  {
    productId: 1,
    name: "Toxic",
    description: "Toksyczny gołąb.",
    price: 10,
    popularity: "85",
    imageUrl: toxicImage,
  },
  {
    productId: 2,
    name: "Angels",
    description: "Szalone aniołki.",
    price: 8,
    popularity: "70",
    imageUrl: angelsImage,
  },
  {
    productId: 3,
    name: "Jeep",
    description: "Piesek jadący Jeepem.",
    price: 5,
    popularity: "60",
    imageUrl: jeepImage,
  },
];

export default products;
