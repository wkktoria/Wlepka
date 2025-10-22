import PageTitle from "./PageTitle";

export default function Contact() {
  const labelStyle =
    "block text-lg font-semibold text-primary dark:text-light mb-2";
  const textFieldStyle =
    "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";

  return (
    <div className="max-w-[1152px] min-h-[852px] mx-auto px-6 py-8 font-primary bg-normalbg dark:bg-darkbg">
      <PageTitle title="Kontakt" />
      <p className="max-w-[768px] mx-auto mt-8 text-gray-600 dark:text-lighter mb-8 text-center">
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Magni, eius,
        cumque, placeat voluptatibus possimus quae perferendis cum sapiente
        quasi excepturi voluptate in natus vero blanditiis. Doloremque maiores
        expedita error. Deleniti.
      </p>

      <form className="space-y-6 max-w-[768px] mx-auto">
        <div>
          <label htmlFor="name" className={labelStyle}>
            Imię
          </label>
          <input
            id="name"
            name="name"
            type="text"
            placeholder="Twoje imię"
            className={textFieldStyle}
            required
            minLength={3}
            maxLength={30}
          />
        </div>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
          <div>
            <label htmlFor="email" className={labelStyle}>
              Adres e-mail
            </label>
            <input
              id="email"
              name="email"
              type="email"
              placeholder="Twój adres e-mail"
              className={textFieldStyle}
              required
            />
          </div>
          <div>
            <label htmlFor="mobileNumber" className={labelStyle}>
              Numer telefonu
            </label>
            <input
              id="mobileNumber"
              name="mobileNumber"
              type="tel"
              placeholder="Twój numer telefonu"
              className={textFieldStyle}
              required
              pattern="^\d{9}$"
              title="Numer musi składać się z 9 cyfr."
            />
          </div>
        </div>
        <div>
          <label htmlFor="message" className={labelStyle}>
            Wiadomość
          </label>
          <textarea
            id="message"
            name="message"
            rows="4"
            placeholder="Twoja wiadomość"
            className={textFieldStyle}
            required
            minLength={5}
            maxLength={500}
          ></textarea>
        </div>
        <div className="text-center">
          <button
            type="submit"
            className="px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
          >
            Wyślij
          </button>
        </div>
      </form>
    </div>
  );
}
