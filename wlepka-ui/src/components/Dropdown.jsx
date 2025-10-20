export default function Dropdown({ label, options, selectedValue }) {
  return (
    <div className="flex items-center gap-2 justify-end pr-12 flex-1 font-primary">
      <label className="text-lg font-semibold text-primary">{label}</label>
      <select
        className="px-3 py-2 text-base border rounded-md transition border-primary focus:ring focus:ring-dark focus:outline-none text-gray-900"
        value={selectedValue}
      >
        {options.map((option, index) => {
          <option key={index} value={option}>
            {option}
          </option>;
        })}
      </select>
    </div>
  );
}
