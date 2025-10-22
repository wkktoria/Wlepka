import PageTitle from "./PageTitle";

export default function About() {
  const h3Style = "text-lg font-semibold text-primary dark:text-light mb-2";
  const pStyle = "text-gray-600 dark:text-lighter";

  return (
    <div className="max-w-[1152px] min-h-[852px] mx-auto px-6 py-8 font-primary">
      <PageTitle title="O nas" />
      <p className="leading-6 mb-8 text-gray-600 dark:text-lighter">
        <span className="text-lg font-semibold text-primary dark:text-light">
          wlepka
        </span>{" "}
        to sklep internetowy z naklejkami, stworzony przez{" "}
        <span className="text-lg font-semibold text-primary dark:text-light">
          wkktoria
        </span>
        {", "}
        oferujący różne naklejki i plakaty.
      </p>

      <h2 className="text-2xl leading-[32px] font-bold text-primary dark:text-light mb-6">
        Dlaczego wlepka?
      </h2>

      <div className="space-y-8">
        <div>
          <h3 className={h3Style}>Najlepsza jakość</h3>
          <p className={pStyle}>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Cumque rem
            eum veritatis repellendus, harum minus earum ipsam odio dignissimos
            nobis ea est quod deserunt placeat explicabo architecto.
            Perspiciatis, quae quos?
          </p>
        </div>
        <div>
          <h3 className={h3Style}>Styl, który pokochasz</h3>
          <p className={pStyle}>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Cumque rem
            eum veritatis repellendus, harum minus earum ipsam odio dignissimos
            nobis ea est quod deserunt placeat explicabo architecto.
            Perspiciatis, quae quos?
          </p>
        </div>
        <div>
          <h3 className={h3Style}>Wspaniała obsługa</h3>
          <p className={pStyle}>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Cumque rem
            eum veritatis repellendus, harum minus earum ipsam odio dignissimos
            nobis ea est quod deserunt placeat explicabo architecto.
            Perspiciatis, quae quos?
          </p>
        </div>
      </div>
    </div>
  );
}
