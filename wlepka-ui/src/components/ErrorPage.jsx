import { Link, useRouteError } from "react-router-dom";
import Footer from "./footer/Footer";
import Header from "./header/Header";
import PageTitle from "./PageTitle";
import errorImage from "../assets/util/error.png";

export default function ErrorPage() {
  const routeError = useRouteError();

  return (
    <div className="flex flex-col min-h-[980px]">
      <Header />
      <main className="flex-grow">
        <div className="py-12 bg-normalbg dark:bg-darkbg font-primary">
          <div className="max-w-4xl mx-auto px-4">
            <PageTitle title={routeError.status} />
          </div>
          <div className="text-center text-gray-600 dark:text-lighter flex flex-col items-center">
            <p className="max-w-[576px] px-2 mx-auto leading-6 mb-4">
              {routeError.data}
            </p>
            <img
              src={errorImage}
              alt="Error"
              className="w-full max-w-[576px] mx-auto mb-6"
            />
            <Link
              to="/home"
              className="py-3 px-6 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter font-semibold"
            >
              Powrót do strony głównej
            </Link>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  );
}
