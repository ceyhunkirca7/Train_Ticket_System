# Tren Bilet Satış ve Sefer Yönetim Sistemi (Train Ticket System)

Bu proje, 5 kişi tarafından geliştirilmişJava programlama dili ve Swing GUI (Grafiksel Kullanıcı Arayüzü) kütüphanesi kullanılarak geliştirilmiş kapsamlı bir **Tren Bileti Satış ve Sefer Yönetim Sistemi**dir.

Uygulama temel olarak iki tür kullanıcı profilini destekler:
1. **Yönetici (Admin):** Tren hatlarını, güzergahları, seferleri ve çalışanları yönetebilen tam yetkili kullanıcıdır.
2. **Müşteri (Yolcu):** Sisteme üye olup, seferleri görüntüleyebilen ve bilet satın alma/rezervasyon işlemlerini gerçekleştirebilen kullanıcıdır.

## Proje Klasör Yapısı ve Teknik Detaylar

Proje bir **Maven** projesidir. Temel bağımlılık olarak JSON veri işleme işlemleri için `Jackson (jackson-databind)` kütüphanesini kullanmaktadır.

- `src/main/java/Backend`: Uygulamanın mantıksal (iş kuralları) ve veri sınıflarının bulunduğu klasördür.
    - `MusteriKayitSistemi`: Müşteri bilgilerini ve kayıt işlemlerini tutar.
    - `SeferYonetimSistemi`: Sefer, Hat, Bilet, Durak ve Çalışan gibi işletmesel nesneleri ve bu nesneleri yöneten sınıfları (CalisanYonetim, SeferYonetim vb.) içerir.
- `src/main/java/GUI`: Kullanıcı arayüzünü (Swing formları ve pencereleri) barındırır.
    - Gerekli ekranlar (örn. LoginForm, Menu, MusteriMenu, SubMenu sınıfları, bilet arama ve satış ekranları) burada tanımlanmıştır.
- `*.json` Dosyaları: Projede veritabanı yerine dosya tabanlı bir sistem (JSON dosyaları) kullanılmıştır.
    - `musteriKayit.json`: Müşterilerin kullanıcı adı, şifre ve iletişim bilgilerini depolar.
    - `seferYonetim.json`: Oluşturulan hatlar, duraklar, düzenlenen seferler ve satılan bilet/koltuk verilerini tutar.
    - `calisanYonetim.json`: Sistem içerisinde kayıtlı olan çalışanların verilerini tutar.

## Temel Özellikler

### Yönetici İşlemleri (Admin Paneli)
- **Hat İşlemleri:** Yeni tren hatları oluşturma, durak ekleme/çıkarma.
- **Sefer İşlemleri:**
  - Yeni sefer oluşturma ve tarih belirleme.
  - Mevcut seferleri düzenleme veya iptal etme.
  - Sisteme kayıtlı tüm seferleri görüntüleme.
  - Sefer takibi.

### Müşteri İşlemleri (Müşteri Paneli)
- Kayıt olma (Sign Up) ve giriş yapma (Login).
- Gün, ay, yıl, başlangıç ve varış duraklarına göre sefer araması yapabilme.
- İlgili sefere ait trenin hangi koltuklarının boş olduğunu grafiksel olarak görebilme (TrainSeatReservation) ve bilet satınalma/rezervasyon.

## Nasıl Çalıştırılır?

Projeyi çalıştırabilmek için bilgisayarınızda **Java Development Kit (JDK) 22** (veya uyumlu bir sürüm) ve **Apache Maven** kurulu olmalıdır. 

### Komut Satırı (Terminal/CMD) ile Çalıştırma

1. Terminali açın ve projenin kök dizinine gidin:
   ```bash
   cd c:\Users\ceyhu\Desktop\codes\TrainTicketSystem
   ```
2. Tüm bağımlılıkları indirmek ve projeyi derlemek için aşağıdaki komutu çalıştırın:
   ```bash
   mvn clean compile
   ```
3. Oturum açma (Login) ekranından uygulamayı başlatmak için:
   ```bash
   mvn exec:java -Dexec.mainClass="GUI.LoginForm"
   ```

### IDE (NetBeans, IntelliJ IDEA, Eclipse) Üzerinden Çalıştırma
Projeyi destekleyen bir IDE'de açın. Maven ayarlarını güncelleyerek (Reload/Refresh Project) kütüphaneleri yükleyin. Ardından `src/main/java/GUI/LoginForm.java` dosyasını bulup sağ tıklayarak **Run (Çalıştır)** seçeneğini seçin.

## Kullanıcı Giriş Bilgileri

- **Yönetici Girişi İçin:** 
  - **Username:** `admin`
  - **Password:** `admin`

- **Müşteri Girişi İçin:**
  İlk çalıştırmada `Sign Up` butonuna tıklayarak yeni bir kullanıcı hesabı oluşturabilir veya daha önceden JSON dosyasına kaydedilmiş bir müşteri hesabıyla giriş yapabilirsiniz. Müşteri bilgileri `musteriKayit.json` üzerinden okunur/yazılır. 
