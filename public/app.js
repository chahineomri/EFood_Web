import { Modal } from 'bootstrap';
import Swal from 'sweetalert2';
document.addEventListener('turbo:before-cache', () => {
    if (document.body.classList.contains('modal-open')) {
        const modalEl = document.querySelector('.modal');
        const modal = Modal.getInstance(modalEl);
        modalEl.classList.remove('fade');
        modal._backdrop._config.isAnimated = false;
        modal.hide();
        modal.dispose();
    }
    if (Swal.isVisible()) {
        Swal.close();
    }
});