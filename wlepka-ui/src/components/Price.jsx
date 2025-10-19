export default function Price({ currency, price }) {
  return (
    <>
      <span className="me-1">{price}</span>
      {currency}
    </>
  );
}
