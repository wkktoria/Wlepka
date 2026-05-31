import React, { useEffect, useState } from "react";

import apiClient from "../api/apiClient";
import {
  Form,
  useActionData,
  useLoaderData,
  useNavigate,
  useNavigation,
} from "react-router-dom";
import { useAuth } from "../store/auth-context";
import { toast } from "react-toastify";
import PageTitle from "./PageTitle";

export default function Profile() {
  const initialProfileData = useLoaderData();
  const actionData = useActionData();
  const navigation = useNavigation();
  const navigate = useNavigate();
  const { logout } = useAuth();

  const [profileData, setProfileData] = useState(initialProfileData);

  const isSubmitting = navigation.state === "submitting";

  const labelStyle =
    "block text-lg font-semibold text-primary dark:text-light mb-2";
  const h2Style =
    "block text-2xl font-semibold text-primary dark:text-light mb-2";
  const textFieldStyle =
    "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";

  useEffect(() => {
    if (actionData?.success) {
      if (actionData.profileData.emailUpdated) {
        sessionStorage.setItem("skipRedirectPath", "true");
        logout();
        toast.success(
          "Wylogowano pomyślnie! Zaloguj się używając nowego adresu e-mail.",
        );
        navigate("/login");
      } else {
        toast.success("Twoje dane zostały pomyślnie zapisane.");
        setProfileData(actionData.profileData);
      }
    }
  }, [actionData]);

  return (
    <div className="max-w-6xl min-h-213 mx-auto px-6 py-8 font-primary bg-normalbg dark:bg-darkbg">
      <PageTitle title="Mój Profil" />

      <Form method="PUT" className="space-y-6 max-w-3xl mx-auto">
        <div>
          <h2 className={h2Style}>Dane osobowe</h2>
          <label htmlFor="name" className={labelStyle}>
            Imię
          </label>
          <input
            id="name"
            type="text"
            name="name"
            placeholder="Twoje imię"
            required
            minLength={5}
            maxLength={30}
            className={textFieldStyle}
            value={profileData.name}
            onChange={(e) =>
              setProfileData((prev) => ({ ...prev, name: e.target.value }))
            }
          />
          {actionData?.errors?.name && (
            <p className="text-red-500 text-sm mt-1">
              {actionData.errors.name}
            </p>
          )}
        </div>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
          <div>
            <label htmlFor="email" className={labelStyle}>
              Adres e-mail
            </label>
            <input
              id="email"
              type="email"
              name="email"
              placeholder="Twój e-mail"
              autoComplete="email"
              required
              className={textFieldStyle}
              value={profileData.email}
              onChange={(e) =>
                setProfileData((prev) => ({ ...prev, email: e.target.value }))
              }
            />
            {actionData?.errors?.email && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.email}
              </p>
            )}
          </div>
          <div>
            <label htmlFor="mobileNumber" className={labelStyle}>
              Numer telefonu
            </label>
            <input
              id="mobileNumber"
              type="tel"
              name="mobileNumber"
              placeholder="Twój numer telefonu"
              required
              pattern="^\d{9}$"
              title="Numer telefonu musi składać się z 9 cyfr."
              className={textFieldStyle}
              value={profileData.mobileNumber}
              onChange={(e) =>
                setProfileData((prev) => ({
                  ...prev,
                  mobileNumber: e.target.value,
                }))
              }
            />
            {actionData?.errors?.mobileNumber && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.mobileNumber}
              </p>
            )}
          </div>
        </div>
        <div>
          <h2 className={h2Style}>Dane adresowe</h2>
          <label htmlFor="street" className={labelStyle}>
            Ulica
          </label>
          <input
            id="street"
            type="text"
            name="street"
            placeholder="Ulica"
            required
            minLength={5}
            maxLength={30}
            className={textFieldStyle}
            value={profileData.street}
            onChange={(e) =>
              setProfileData((prev) => ({ ...prev, street: e.target.value }))
            }
          />
          {actionData?.errors?.street && (
            <p className="text-red-500 text-sm mt-1">
              {actionData.errors.street}
            </p>
          )}
        </div>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
          <div>
            <label htmlFor="postalCode" className={labelStyle}>
              Kod pocztowy
            </label>
            <input
              id="postalCode"
              type="text"
              name="postalCode"
              placeholder="Kod pocztowy"
              required
              pattern="^\d{2}-\d{3}$"
              title="Kod pocztowy musi być w formacie xx-xxx"
              className={textFieldStyle}
              value={profileData.postalCode}
              onChange={(e) =>
                setProfileData((prev) => ({
                  ...prev,
                  postalCode: e.target.value,
                }))
              }
            />
            {actionData?.errors?.postalCode && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.postalCode}
              </p>
            )}
          </div>
          <div>
            <label htmlFor="country" className={labelStyle}>
              Kraj
            </label>
            <input
              id="country"
              type="text"
              name="country"
              placeholder="Kraj"
              required
              minLength={5}
              maxLength={30}
              className={textFieldStyle}
              value={profileData.country}
              onChange={(e) =>
                setProfileData((prev) => ({ ...prev, country: e.target.value }))
              }
            />
            {actionData?.errors?.country && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.country}
              </p>
            )}
          </div>
        </div>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
          <div>
            <label htmlFor="city" className={labelStyle}>
              Miasto
            </label>
            <input
              id="city"
              type="text"
              name="city"
              placeholder="Miasto zamieszkania"
              required
              minLength={5}
              maxLength={30}
              className={textFieldStyle}
              value={profileData.city}
              onChange={(e) =>
                setProfileData((prev) => ({ ...prev, city: e.target.value }))
              }
            />
            {actionData?.errors?.city && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.city}
              </p>
            )}
          </div>
          <div>
            <label htmlFor="state" className={labelStyle}>
              Województwo
            </label>
            <input
              id="state"
              type="text"
              name="state"
              placeholder="Województwo"
              required
              minLength={2}
              maxLength={20}
              className={textFieldStyle}
              value={profileData.state}
              onChange={(e) =>
                setProfileData((prev) => ({
                  ...prev,
                  state: e.target.value,
                }))
              }
            />
            {actionData?.errors?.state && (
              <p className="text-red-500 text-sm mt-1">
                {actionData.errors.state}
              </p>
            )}
          </div>
        </div>
        <div className="text-center">
          <button
            type="submit"
            disabled={isSubmitting}
            className="px-6 py-2 mt-8 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
          >
            {isSubmitting ? "Zapisywanie..." : "Zapisz"}
          </button>
        </div>
      </Form>
    </div>
  );
}

export async function profileLoader() {
  try {
    const response = await apiClient.get("/profile");
    return response.data;
  } catch (error) {
    throw new Response(
      error.response?.data?.errorMessage ||
        error.message ||
        "Nie udało się pobrać informacji o profilu. Spróbuj ponownie.",
      { status: error.status || 500 },
    );
  }
}

export async function profileAction({ request }) {
  const data = await request.formData();

  const profileData = {
    name: data.get("name"),
    email: data.get("email"),
    mobileNumber: data.get("mobileNumber"),
    street: data.get("street"),
    city: data.get("city"),
    state: data.get("state"),
    postalCode: data.get("postalCode"),
    country: data.get("country"),
  };

  try {
    const response = await apiClient.put("/profile", profileData);
    return { success: true, profileData: response.data };
  } catch (error) {
    if (error.response?.status === 40) {
      return { success: false, errors: error.response?.data };
    }
    throw new Response(
      error.response?.data?.errorMessage ||
        error.message ||
        "Nie udało się zapisać informacji. Spróbuj ponownie.",
      { status: error.status || 500 },
    );
  }
}
