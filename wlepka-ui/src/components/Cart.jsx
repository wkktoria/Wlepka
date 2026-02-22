import { Link, useNavigate } from "react-router-dom";
import PageTitle from "./PageTitle";
import emptyCartImage from "../assets/util/empty-cart.png";
import { useCart } from "../store/cart-context";
import { useMemo } from "react";
import CartTable from "./CartTable";

export default function Cart() {
  const navigation = useNavigate();
  const { cart } = useCart();

  const isCartEmpty = useMemo(() => cart.length === 0, [cart.length]);

  return (
    <div className="min-h-[852px] py-12 bg-normalbg dark:bg-darkbg font-primary">
      <div className="max-w-4xl mx-auto px-4">
        <PageTitle title="Koszyk" />
        {!isCartEmpty ? (
          <>
            <CartTable />
            <div className="flex justify-between mt-8 space-x-4">
              <Link
                to="/home"
                className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition"
              >
                Powrót do strony głównej
              </Link>
              <button className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition">
                Przejdź do płatności
              </button>
            </div>
          </>
        ) : (
          <div className="text-center text-gray-600 dark:text-lighter flex flex-col items-center">
            <p className="max-w-[576px] px-2 mx-auto text-base mb-4">
              Wygląda na to, że Twój koszyk jest pusty.
            </p>
            <img
              src={emptyCartImage}
              alt="Empty cart"
              className="max-w-[300px] mx-auto mb-6 dark:bg-light dark:rounded-md"
            />
            <Link
              to="/"
              className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition"
            >
              Powrót do strony głównej
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}
