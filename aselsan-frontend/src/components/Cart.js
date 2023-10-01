import { Card, Button, Container, Row, Col } from 'react-bootstrap'
import PropTypes from 'prop-types'

const Cart = ({ cart, onRefundClick, onFinishClick }) => {
    return (
        <Card style={{ height: '30rem' }}>
            <Card.Body>
                <Card.Title>Items In Cart</Card.Title>
                <Card.Text style={{ height: '22rem', overflowY: 'auto' }}>
                    {cart.length === 0 && 'No items are added yet!'}
                    {cart.map((item, idx) => (
                        <Container key={idx}>
                            <Row className='d-flex align-items-center'>
                                <Col>{item.name}</Col>
                                <Col>{item.price} ₺</Col>
                                <Col><Button className='m-1' variant='danger' onClick={() => onRefundClick(idx)}>Refund</Button></Col>
                            </Row>
                        </Container>
                    ))}
                </Card.Text>
                {
                    cart.length !== 0 &&
                    <Button variant='success' onClick={() => onFinishClick()}>Finish</Button>
                }
            </Card.Body>
        </Card>
    );
}

Cart.propTypes = {
    cart: PropTypes.arrayOf(PropTypes.shape({
        imgURL: PropTypes.string.isRequired,
        name: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        stock: PropTypes.number.isRequired,
    })).isRequired,
    onRefundClick: PropTypes.func.isRequired,
    onFinishClick: PropTypes.func.isRequired,
}

export default Cart;