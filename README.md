# Dictionary



## Thông tin thành viên nhóm
(Tên: gmail, @gitlab)
- Bùi Minh Quang: usociety03@gmail.com, @yelaco
- Hà Quang Nhuệ: nhuebk203@gmail.com, @hanhue
- Nguyễn Hữu Trong: tronglacnhue@gmail.com, @tronglacnhue

## Phiên bản Java dùng để compile
JDK-17.0.3

## Cách chạy

- Chạy file RunDictionary.vbs để mở ứng dụng hoàn chỉnh nhất (không nên chạy file jar hay subrun.bat vì sẽ bị lỗi tiếng Việt).
- Nếu bị lỗi hiển thị tiếng việt khi ứng dụng bật Kompozer: ở Kompozer vào mục View->Character Encoding->Auto-Detect->Universal để hiển thị dưới dạng UTF-8.
- Lưu ý: Chỉ chạy được trên Windows

## Giới thiệu về từ điển

- Có đầy đủ chức năng search, thêm, sửa, xóa từ.
- Có bổ sung tính năng 'từ yêu thích' và 'lịch sử tra từ'.
- Dùng Google Translate API, Java Speech API, Javazoom và lấy dữ liệu âm thanh từ Google database để dịch văn bản và phát âm tiếng anh.
- Có giao diện thân thiện người dùng. (Sử dụng java swing kết hợp java awt)
- Thuật toán tìm kiếm đã được tối ưu hóa thành binary search.
- Tích hợp cơ sở dữ liệu SQLite thông qua Java Database Connectivity API để kết nối và thực thi lệnh SQL.
## Cách dùng từ điển

- Vào ứng dụng ấn nút dưới cùng ở thanh bên trái để hiển thị thông tin về cách dùng từ điển.

## Công việc của các thành viên nhóm

- Hà Quang Nhuệ: làm UI cho ứng dụng sử dụng swing, awt và thư viện Layout của netbeans.
- Bùi Minh Quang: làm UX và tích hợp Google Translate API, Java Speech API, lấy dữ liệu âm thanh từ Google database.
- Nguyễn Hữu Trọng: làm backend sử dụng Java Database Connectivity API để kết nối cơ sơ dữ liệu và thực thi lệnh SQL cho từ điển.

## Chi tiết hơn về các API và thư viện đã sử dụng

- Java Swing và thư viện Layout để thiết kế giao diện, Java Awt để tạo các listener bắt event cho Component.
- Google Translate API được tích hợp thông qua việc viết app script trên https://www.google.com/script/start/ và nối với ứng dụng để dịch online.
- Dữ liệu phát âm tiếng anh từ Google Database được tải xuống tự động khi gọi đến và dùng để phát âm thanh qua javazoom.
- Java Speech API được tích hợp để phát âm tiếng anh khi không có kết nối mạng.
- Java Database Connectivity API cung cấp khả năng mở kết nối với cơ sở dữ liệu và thực thi các query hoặc update.
