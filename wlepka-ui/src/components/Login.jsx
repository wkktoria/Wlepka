import { Link } from "react-router-dom";
import PageTitle from "./PageTitle";

export default function Login() {
  const labelStyle =
    "block text-lg font-semibold text-primary dark:text-light mb-2";
  const textFieldStyle =
    "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";

  return (
    <div className="min-h-[852px] flex items-center justify-center font-primary dark:bg-darkbg">
      <div className="bg-white dark:bg-gray-700 shadow-md rounded-lg max-w-md w-full px-8 py-6">
        <PageTitle title="Zaloguj się" />
        <form className="space-y-6">
          <div>
            <label htmlFor="username" className={labelStyle}>
              Nazwa użytkownika
            </label>
            <input
              id="username"
              name="username"
              type="text"
              placeholder="Twoja nazwa użytkownika"
              required
              className={textFieldStyle}
            />
          </div>
          <div>
            <label htmlFor="password" className={labelStyle}>
              Hasło
            </label>
            <input
              id="password"
              name="password"
              type="password"
              placeholder="Twoja hasło"
              required
              minLength={8}
              className={textFieldStyle}
            />
          </div>
          <div>
            <button
              type="submit"
              className="w-full px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
            >
              Zaloguj się
            </button>
          </div>
        </form>
        <p className="text-center text-gray-600 dark:text-gray-400 mt-4">
          Nie masz konta?{" "}
          <Link
            to="/register"
            className="text-primary dark:text-light hover:text-dark dark:hover:text-primary transition duration-200"
          >
            Zarejestruj się
          </Link>
          .
        </p>
      </div>
    </div>
  );
}
