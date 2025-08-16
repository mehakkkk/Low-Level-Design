public class BuildPackage {

    public static void main(String[] args) {
        VacationPackage v1 = VacationPackage.getVactationBuilder()
                                .setFlight("Mumbai to Hanoi, Ho Chi Minh to Mumbai")
                                .setHotel("3-star hotels")
                                .setAttractions("Da Nang")
                                .build();

        System.out.println(v1);
    }
}
