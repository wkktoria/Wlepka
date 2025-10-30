import PageTitle from "./PageTitle";
import {
  Form,
  useActionData,
  useNavigation,
  useSubmit,
} from "react-router-dom";
import apiClient from "../api/apiClient";
import { useEffect, useRef } from "react";
import { toast } from "react-toastify";

export default function Contact() {
  const actionData = useActionData();
  const formRef = useRef(null);
  const navigation = useNavigation();
  const submit = useSubmit();
  const isSubmitting = navigation.state === "submitting";

  useEffect(() => {
    if (actionData?.success) {
      formRef.current?.reset();
      toast.success("Twoja wiadomość została wysłana!");
    }
  }, [actionData]);

  const handleSubmit = (event) => {
    event.preventDefault();

    const userConfirmed = window.confirm("Na pewno chcesz wysłać wiadomość?");

    if (userConfirmed) {
      const formData = new FormData(formRef.current);
      submit(formData, { method: "post" });
    } else {
      toast.info("Anulowano wysyłanie wiadomości.");
    }
  };

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

      <Form
        ref={formRef}
        method="post"
        onSubmit={handleSubmit}
        className="space-y-6 max-w-[768px] mx-auto"
      >
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
            disabled={isSubmitting}
            className="px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
          >
            {isSubmitting ? "Wysyłanie" : "Wyślij"}
          </button>
        </div>
      </Form>
    </div>
  );
}

export async function contactAction({ request, params }) {
  const data = await request.formData();
  const contactData = {
    name: data.get("name"),
    email: data.get("email"),
    mobileNumber: data.get("mobileNumber"),
    message: data.get("message"),
  };
  try {
    await apiClient.post("/contacts", contactData);
    return { success: true };
  } catch (error) {
    throw new Response(
      error.message || "Nie udało się wysłać wiadomości. Spróbuj ponownie.",
      {
        status: error.status || 500,
      }
    );
  }
}
