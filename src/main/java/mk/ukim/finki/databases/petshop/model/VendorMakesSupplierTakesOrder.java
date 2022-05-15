package mk.ukim.finki.databases.petshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class VendorMakesSupplierTakesOrder {

    @EmbeddedId
    private VendorMakesSupplierTakesOrderId id;

    @ManyToOne
    @MapsId("idOrder")
    private Order order;

    @ManyToOne
    @MapsId("idSupplier")
    private Supplier supplier;

    @ManyToOne
    @MapsId("idVendor")
    private Vendor vendor;

    @Embeddable
    public static class VendorMakesSupplierTakesOrderId implements Serializable {
        private Long idOrder;
        private Long idSupplier;
        private Long idVendor;

        public VendorMakesSupplierTakesOrderId() {
        }

        public VendorMakesSupplierTakesOrderId(Long idOrder, Long idSupplier, Long idVendor) {
            this.idOrder = idOrder;
            this.idSupplier = idSupplier;
            this.idVendor = idVendor;
        }
    }

}
