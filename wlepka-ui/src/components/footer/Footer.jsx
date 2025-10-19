import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "./footer.css";

export default function Footer() {
  return (
    <footer className="footer">
      Built with{" "}
      <FontAwesomeIcon
        icon={faHeart}
        className="footer-icon"
        aria-hidden="true"
      />
      by{" "}
      <a href="https://github.com/wkktoria" target="_blank" rel="noreferrer">
        wkktoria
      </a>
    </footer>
  );
}
