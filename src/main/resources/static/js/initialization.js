// Khoi tao danh sach san pham (Tranh)
function createProduct() {
    // Kiểm tra nếu danh sách sản phẩm (tranh) chưa tồn tại trong localStorage
    if (localStorage.getItem('products') == null) {
        // Định nghĩa mảng các sản phẩm là tranh
        let products = [{
            id: 1,
            status: 1,
            title: 'Bình minh trên biển', // Tên bức tranh
            img: 'https://th.bing.com/th/id/OIP.GZNhEQHrAKRU1l0PMG-4RQHaL_?rs=1&pid=ImgDetMain', // Đường dẫn ảnh của bức tranh
            category: 'Tranh phong cảnh', // Thể loại tranh
            price: 1500000, // Giá của bức tranh
            desc: 'Một bức tranh sơn dầu tươi sáng mô tả cảnh bình minh rực rỡ trên mặt biển tĩnh lặng, với những tia nắng đầu tiên chiếu qua những đám mây bồng bềnh.' // Mô tả bức tranh
        },
            {
                id: 2,
                status: 1,
                title: 'Chân dung cô gái', // Tên bức tranh
                img: 'https://th.bing.com/th/id/OIP.X-mVVVWd_7_6uV58KcDDeQHaJ4?w=133&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', // Đường dẫn ảnh của bức tranh
                category: 'Tranh chân dung', // Thể loại tranh
                price: 2500000, // Giá của bức tranh
                desc: 'Bức tranh chì tinh xảo khắc họa vẻ đẹp dịu dàng và ánh mắt cuốn hút của một cô gái trẻ.' // Mô tả bức tranh
            },
            {
                id: 3,
                status: 1,
                title: 'Phố cổ buổi chiều', // Tên bức tranh
                img: 'https://th.bing.com/th/id/OIP.3IPIi0hBZxOZVlp9DbjP1wHaFj?w=223&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', // Đường dẫn ảnh của bức tranh
                category: 'Tranh phong cảnh đô thị', // Thể loại tranh
                price: 1800000, // Giá của bức tranh
                desc: 'Mô tả một góc phố cổ với mái ngói rêu phong, những gánh hàng rong và ánh nắng chiều vàng dịu.' // Mô tả bức tranh
            },
            {
                id: 4,
                status: 1,
                title: 'Vũ điệu màu sắc trừu tượng', // Tên bức tranh
                img: 'https://th.bing.com/th/id/OIP.QeTjfXQcYfCidAPy8FIGLgHaF1?w=242&h=190&c=7&r=0&o=5&dpr=1.3&pid=1.7', // Đường dẫn ảnh của bức tranh
                category: 'Tranh trừu tượng', // Thể loại tranh
                price: 3000000, // Giá của bức tranh
                desc: 'Sự kết hợp ngẫu hứng và hài hòa của các mảng màu và hình khối, tạo nên một cảm giác động và đầy năng lượng.' // Mô tả bức tranh
            },
            {
                id: 5,
                status: 1,
                title: 'Cây cầu cũ kỹ', // Tên bức tranh
                img: 'https://th.bing.com/th/id/OIP.1YPnBg05eSVB3vqaXcV0FAHaLS?w=115&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', // Đường dẫn ảnh của bức tranh
                category: 'Tranh phong cảnh', // Thể loại tranh
                price: 1600000, // Giá của bức tranh
                desc: 'Một bức tranh màu nước nhẹ nhàng về một cây cầu đá cổ bắc qua dòng sông êm đềi, bao quanh là cây cối xanh tươi.' // Mô tả bức tranh
            },
        ]
        // Lưu danh sách sản phẩm (tranh) vào localStorage dưới dạng chuỗi JSON
        localStorage.setItem('products', JSON.stringify(products));
    }
}

// Gọi hàm createProduct khi cửa sổ trình duyệt được tải hoàn toàn
window.onload = createProduct();