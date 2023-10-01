import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import PropTypes from 'prop-types';

const OrderModal = ({ showModal, setShowModal, orderDetails }) => {

    function calculateTotal(items) {
        return items.reduce((total, item) => total + (item.price * item.stock), 0);
    }

    return (
        <Modal show={showModal} onHide={() => setShowModal(false)}>
            <Modal.Body>
                <h4>Order is finished!</h4>
                <br />
                <p>Thank you for your purchase!</p>
                <p>You have paid <b>{calculateTotal(orderDetails.items)} ₺</b> and you have <b>{orderDetails.change} ₺</b> change!</p>

                <p>You have bought:
                    <ul>
                        {orderDetails.items.map((item, idx) => (
                            <li key={idx}>{item.name} x {item.stock}</li>
                        ))}
                    </ul></p>
            </Modal.Body>

            <Modal.Footer>
                <Button variant="primary" onClick={() => setShowModal(false)}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

OrderModal.propTypes = {
    showModal: PropTypes.bool.isRequired,
    setShowModal: PropTypes.func.isRequired,
    orderDetails: PropTypes.shape({
        items: PropTypes.arrayOf(PropTypes.shape({
            imgURL: PropTypes.string.isRequired,
            name: PropTypes.string.isRequired,
            price: PropTypes.number.isRequired,
            stock: PropTypes.number.isRequired,
        })).isRequired,
        change: PropTypes.number.isRequired,
    }).isRequired,
}

export default OrderModal;