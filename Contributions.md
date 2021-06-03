## Clean Code
- Subteam 1: Vũ Quang Đại, Lê Minh Đức
- Subteam 2: Nguyễn Đình Đức, Nguyễn Mạnh Đức



## Strategy Pattern
- Subteam 1: Vũ Quang Đại, Lê Minh Đức
- Subteam 2: Nguyễn Đình Đức, Nguyễn Mạnh Đức

Sử dụng Strategy Pattern trong yêu cầu thêm việc xử lý payment bằng domestic card.\

Phân tích:
- Về các loại thẻ: tạo mới một lớp cha Card, để Domestic Card và Credit Card kế thừa lớp này.
- PaymentTransaction: có thuộc tính là Card thay vì CreditCard.
- Trong PaymentController, cung cấp phương thức để thanh toán với từng loại card khác nhau. Ví dụ: payOrderByCreditCard, payOrderByDomesticCard. Trong lớp giao diện PaymentScreenHandler, tùy theo lựa chọn của người dùng để gọi đến phương thức tương ứng. Bên trong các phương thức thanh toán này sẽ tạo ra object Card (CreditCard, DomesticCard) tương ứng.
- Strategy Pattern sẽ được áp dụng với InterbankSubsystem là context và các InterbankSubsystemController là các strategy (InterbankSubsystemCreditCardController và InterbankSubsystemDomesticCardController) kế thừa từ 1 interface là InterbankSubsystemControllerInterface.
- Vì cách xử lý payload và tạo transaction sau khi giao dịch giữa 2 loại CrediCard và DomesticCard là khác nhau, nên với mỗi loại cần tạo 1 lớp InterbankPayloadConverter riêng để xử lý.
