import { Card, Button } from 'react-bootstrap'

const Cart = ({ cart, onRefundClick, onFinishClick }) => {
    return (
        <Card style={{ width: '18rem', height: '29rem' }}>
            <Card.Body>
                <Card.Title><h3>Bought Items</h3></Card.Title>
                <Card.Text>

                    {cart.length === 0 && <li>No items are bought.</li>}
                    {cart.map((item, idx) => (
                        <div className='d-flex justify-content-between' key={idx}>
                            {item.name}
                            <Button variant='danger' onClick={() => onRefundClick(idx)}>Refund</Button>
                        </div>
                    ))}

                </Card.Text>
                <Button variant="primary" onClick={onFinishClick}>Finish</Button>
            </Card.Body>
        </Card >

    );
}

export default Cart;