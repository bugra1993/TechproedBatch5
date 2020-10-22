package techproedbatch5;


import org.codehaus.jackson.annotate.JsonProperty;

public class BookingDates {

    public BookingDates() {

    }

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;


    @JsonProperty("checkin")
    public String getCheckin() {
        return checkin;
    }

    @JsonProperty("checkin")
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    @JsonProperty("checkout")
    public String getCheckout() {
        return checkout;
    }

    @JsonProperty("checkout")
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingDates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
// Her variable icin mutlak getter ve setter olmali
/*
    pojo da olmasi gerekenler:
    1) Json da key olanlar icin variable olusturun ve bu variable larin access modifier lari private olmali.
    2) Her variable icin getter ve setter lar olmali.
    3) Parametresiz construction olmali
    4) olusturdugumuz variable lar icin parametreli constructer lari olusturalim.
    5) toString methodu olusturuyoruz.


 */
