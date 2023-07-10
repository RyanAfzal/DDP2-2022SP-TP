
import java.awt.Dimension;

/**
 * 
 * Perintah.java
 * <br><br>
 * Class {@code Perintah} merepresentasikan perintah-perintah umum yang 
 * dapat diberikan kepada kura-kura. Termasuk dalam class ini adalah
 * proses untuk membaca input (saat ini baru melalui satu baris perintah)
 * dan memanggil method yang berkesesuaian.
 * Dalam kelas ini juga disediakan method-method yang merupakan kumpulan-kumpulan
 * perintah berurutan yang dapat diterima oleh kurakura dan menghasilkan gambar 
 * tertentu. 
 * <br><br>
 * Tugas anda pada file ini: <br>
 * - Lengkapi javadoc comment pada tiap deklarasi method.<br>
 * - Lengkapi inline comment untuk tiap baris perintah yang penting.<br>
 * - Perbaiki method {@code lakukan} agar tidak menimbulkan error bila input salah<br>
 * - Buat (1) perintah {@code mundur <x>}" agar kura-kura bisa mundur sebanyak x pixel satuan.
 * - Buat (2) perintah {@code hadap kanan} dan {@code hadap kiri} yang akan membuat kura-kura 
 *   menghadap ke kanan (rotasi 90) dan ke kiri (rotasi -90) 
 * - Dapatkah anda membuat (3) kura-kura mengenali perintah 
 *   {@code loop 10 perintah-perintah} <br>
 *   yang artinya melakukan perintah-perintah sebanyak 10 kali? <br>
 *   contoh: "loop 10 rotasi 30 maju 30" <br>
 *           yang artinya akan melakukan perintah "rotasi 30", dilanjutkan <br>
 *           perintah "maju 30", secara berulang-ulang sebanyak 10 kali<br>
 *   contoh: "loop 5 maju 20 hadap kanan maju 30" <br>
 *           yang artinya akan melakukan perintah "maju 20", dilanjutkan <br>
 *           "hadap kanan", kemudian perintah "maju 10", <br> 
 *           secara berulang-ulang sebanyak 5 kali<br>
 * 
 * @author Ade Azurat for DPBO 2008 @ Fasilkom UI
 * @author Ade Azurat for DDP2 2023 @ Fasilkom UI
 */
public class Perintah {
    Canvas canvas;
    Kurakura kurakuraku; 
    
    /** Creates a new instance of Perintah */
    public Perintah(Kurakura k, Canvas canvas) {
        kurakuraku = k;
        this.canvas = canvas;
    }

    /**
     * Method ini ditambahkan untuk memeriksa apakah string numeric atau tidak
     * dengan cara apa bila string tidak bisa dikonversi maka bukan numeric, jika bisa dikonversi
     * maka string tersebut numeric
     * @param s adalah parameter berupa string
     * @return mengembalikan value berupa boolean true atau false
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method ini untuk memberi tahu komputer apa yang harus dilakukan oleh turtle
     * @param inputPerintah parameter input dari user berupa string untuk perintah  yang harus dijalankan turtle
     * @return akan mengembalikan value berupa pesan jika input tidak sesuai dengan yang dibutuhkan suatu perintah
     */
    // Dapatkan anda membuat method ini lebih baik dan lebih mudah ditambahkan
    // atau di ubah? 
    public String lakukan(String inputPerintah){
        String[] in = inputPerintah.split(" ");
        if (in[0].equalsIgnoreCase("loop")){
            if (isInteger(in[1]) == true){
                for (int k = 0 ; k < Integer.parseInt(in[1]) ; k++){
                    try{
                        for (int i = 2 ; i < in.length ; i++){
                            if (in[i].equalsIgnoreCase("reset")){
                                kurakuraku.reset();
                            }

                            else if (in[i].equalsIgnoreCase("selesai")){
                                System.exit(0);
                            }

                            else if (in[i].equalsIgnoreCase("pohon")){
                                buatPohon();
                            }

                            else if (in[i].equalsIgnoreCase("maju")){
                                if (isInteger(in[i+1]) == true){
                                    kurakuraku.maju(Integer.parseInt(in[i+1]));
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("mundur")){
                                if (isInteger(in[i+1]) == true){
                                    kurakuraku.mundur(Integer.parseInt(in[i+1]));
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("rotasi") && in[i+1].equalsIgnoreCase("derajat") == false){
                                if (isInteger(in[i+1]) == true){
                                    kurakuraku.rotasi(Integer.parseInt(in[i+1]));
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("rotasi") && in[i+1].equalsIgnoreCase("derajat")){
                                if (isInteger(in[i+2]) == true){
                                    kurakuraku.rotasiDerajat(Integer.parseInt(in[i+2]));
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("kotak")){
                                if (isInteger(in[i+1]) == true){
                                    buatKotak(Integer.parseInt(in[i+1]));
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("segitiga") && in[i+1].equalsIgnoreCase("siku-siku") == false){
                                if (isInteger(in[i+1]) == true){
                                    buatSegitiga(Integer.parseInt(in[i+1]));
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("jejak")){
                                try{
                                    kurakuraku.setJejak(Boolean.parseBoolean(in[i+1]));
                                }

                                catch (Exception e){
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("pindah")){
                                try{
                                    kurakuraku.setPosition(new Dimension(Integer.parseInt(in[i+1]),Integer.parseInt(in[i+2])));
                                }

                                catch (Exception e){
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                            }

                            else if (in[i].equalsIgnoreCase("persegi")){
                                if (isInteger(in[i+1]) == true && isInteger(in[i+2]) == true){
                                    int panjang = Integer.parseInt(in[i+1]);
                                    int lebar = Integer.parseInt(in[i+2]);

                                    if (panjang >= lebar){
                                        buatPersegi(panjang, lebar);
                                    }
                                    else{
                                        canvas.repaint();
                                        return "Ukuran memiliki kesalahan";
                                        }
                                }
                                else{
                                    canvas.repaint();
                                    return "Perintah kekurangan argumen";
                                }
                                
                            }

                            else if (in[i].equalsIgnoreCase("segitiga") && in[i+1].equalsIgnoreCase("siku-siku")){
                                if (isInteger(in[i+2]) == true && isInteger(in[i+3]) == true){
                                    buatSegitigaSikuSiku(Integer.parseInt(in[i+2]),Integer.parseInt(in[i+3]));
                                }
                            }

                            else{
                                if (isInteger(in[i]) == false){
                                    canvas.repaint();
                                    return "Perintah tidak dipahami";
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        canvas.repaint();
                        return "Perintah kekurangan argumen";
                    }
                }
            }

            else{
                canvas.repaint();
                return "Perintah kekurangan argumen";
            }

            canvas.repaint();    
            return "Perintah sudah dilaksanakan.";
        }
        
        else if (in.length == 1){
            if (in[0].equalsIgnoreCase("reset"))
                kurakuraku.reset();
            else if (in[0].equalsIgnoreCase("pohon"))
                buatPohon();
            else if (in[0].equalsIgnoreCase("selesai"))
                System.exit(0);
            else{
                canvas.repaint();
                return "Perintah kekurangan argumen";
            }
        }

        else if (in.length == 2){
            if (in[0].equalsIgnoreCase("maju"))
                    kurakuraku.maju(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("mundur"))
                    kurakuraku.mundur(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("hadap") && in [1].equalsIgnoreCase("kanan")){
                    kurakuraku.rotasi(90);}
            else if (in[0].equalsIgnoreCase("hadap") && in [1].equalsIgnoreCase("kiri")){
                    kurakuraku.rotasi(-90);}
            else if (in[0].equalsIgnoreCase("rotasi"))
                    kurakuraku.rotasi(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("kotak"))
                    buatKotak(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("segitiga"))
                    buatSegitiga(Integer.parseInt(in[1]));        
            else if (in[0].equalsIgnoreCase("jejak"))
                    kurakuraku.setJejak(Boolean.parseBoolean(in[1]));
            else if (in[0].equalsIgnoreCase("boxes"))
                    boxes(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("sierpinski"))
                    sierpinski(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("persegi")){
                    canvas.repaint();
                    return "Perintah kekurangan argumen";
            }
            else if (in[0].equalsIgnoreCase("segitiga") && in[1].equalsIgnoreCase("siku-siku")){
                canvas.repaint();
                return "Perintah kekurangan argumen";
            }

            else if (in[0].equalsIgnoreCase("rotasi") && in[1].equalsIgnoreCase("derajat")){
                canvas.repaint();
                return "Perintah kekurangan argumen";
            }

            else{
                    canvas.repaint(); 
                    return "Perintah tidak dipahami.";
            }
        }

        else if (in.length == 3){
            if (in[0].equalsIgnoreCase("persegi")){
                    int panjang = Integer.parseInt(in[1]);
                    int lebar = Integer.parseInt(in[2]);

                    if (panjang >= lebar)
                        buatPersegi(panjang, lebar);
                    else{
                        canvas.repaint();
                        return "Ukuran memiliki kesalahan";
                        }
            }

            else if (in[0].equalsIgnoreCase("rotasi") && in[1].equalsIgnoreCase("derajat"))
                    kurakuraku.rotasiDerajat(Integer.parseInt(in[2]));
            
            else if (in[0].equalsIgnoreCase("pindah"))
                    kurakuraku.setPosition(new Dimension(Integer.parseInt(in[1]),Integer.parseInt(in[2])));

            else if (in[0].equalsIgnoreCase("segitiga") && in[1].equalsIgnoreCase("siku-siku")){
                    canvas.repaint();
                    return "Perintah kekurangan argumen";
            }

            else{
                canvas.repaint(); 
                return "Perintah tidak dipahami.";
            }
        }

        else if (in.length == 4){
            if (in[0].equalsIgnoreCase("segitiga") && in[1].equalsIgnoreCase("siku-siku")){
                buatSegitigaSikuSiku(Integer.parseInt(in[2]),Integer.parseInt(in[3]));
            }

            else{
                canvas.repaint(); 
                return "Perintah tidak dipahami.";
            }
        }

        canvas.repaint();    
        return "Perintah sudah dilaksanakan.";
    }
    
    /**
     * Method ini untuk kura-kura menggambar kotak
     * @param ukuran parameter ukuran kotak yang diinginkan
     */
    public void buatKotak(int ukuran ){        
        for (int i=0;i<4;i++){
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(90);
        }
    }

    /**
     * Method ini untuk kura-kura menggambar segitiga
     * @param ukuran parameter ukuran segitiga yang diinginkan
     */
    public Dimension buatSegitiga(int ukuran){
        // TODO: Lengkapi isi method ini agar kura-kura bisa membuat segitiga sama sisi
        kurakuraku.maju(ukuran);
        kurakuraku.rotasi(-120);
        kurakuraku.maju(ukuran);
        Dimension posisiAtas = kurakuraku.getPosition();
        kurakuraku.rotasi(-120);
        kurakuraku.maju(ukuran);   
        
        return posisiAtas;
    }        
    
    /**
     * Method ini untuk kura-kura menggambar persegi (Lebih mengarah ke persegi panjang)
     * @param panjang   parameter ukuran panjang persegi yang diinginkan
     * @param lebar     parameter ukuran lebar persegi yang diinginkan
     */
    public void buatPersegi(int panjang, int lebar){
        kurakuraku.maju(panjang);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(lebar);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(panjang);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(lebar);
        
    }

    /**
     * Method ini untuk kura-kura menggambar segitiga siku-siku
     * @param panjangAlas parameter ukuran alas segitiga siku-siku yang diinginkan
     * @param tinggi      parameter ukuran tinggi segitiga siku-siku yang diinginkan
     */
    public void buatSegitigaSikuSiku(int panjangAlas, int tinggi){
        double miring = Math.sqrt(Math.pow(panjangAlas,2)+Math.pow(tinggi,2));
        double sudutBerhadapanAlasRad = Math.asin(panjangAlas / miring * Math.sin(Math.PI/2));
        double sudutBerhadapanAlasDerajat = sudutBerhadapanAlasRad * 180/Math.PI;               //rumus ini juga bisa diganti dengan Math.toDegrees()
        double derajatRotasi = 90 + (90-sudutBerhadapanAlasDerajat);

        kurakuraku.maju(panjangAlas);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(tinggi);
        kurakuraku.rotasi(-derajatRotasi);
        kurakuraku.maju(miring);
    }

    /**
     * Method ini untuk kura-kura menggambar nested boxes
     * @param ukuran parameter ukuran sisi kotak
     */
    public void boxes(int ukuran){
        if (ukuran >= 0){
        buatKotak(ukuran);
        kurakuraku.setJejak(false);
        kurakuraku.maju(10);
        kurakuraku.rotasi(90);
        kurakuraku.maju(10);
        kurakuraku.rotasi(-90);
        kurakuraku.setJejak(true);
        boxes(ukuran-20);
        }
    }
    
    public void sierpinski(int ukuran){
        buatSegitiga(ukuran);
        kurakuraku.rotasi(-120);
        sierpinskiRekursif(ukuran/2);
    }

    public void sierpinskiRekursif (int ukuran){
        if (ukuran > 2){
            kurakuraku.maju(ukuran);
            Dimension posisiAwal = kurakuraku.getPosition();
            kurakuraku.rotasi(-60);
            Dimension posisiAtas = buatSegitiga(ukuran);
            kurakuraku.rotasi(-60);
            kurakuraku.setPosition(posisiAwal);
            kurakuraku.rotasi(-180);
            kurakuraku.maju(ukuran/2);
            kurakuraku.rotasi(120);
            buatSegitiga(ukuran/2);
            kurakuraku.setPosition(posisiAtas);
            kurakuraku.rotasi(-60);
            kurakuraku.maju(ukuran/2);
            kurakuraku.rotasi(-60);
            buatSegitiga(ukuran/2);
            kurakuraku.setPosition(posisiAwal);
            kurakuraku.rotasi(-60);
            sierpinskiRekursif(ukuran/2);
        }
    }

    /**
     * Method ini untuk kura-kura menggambar pohon
     * dengan modifikasi diujungnya ditambahkan persegi pada method yang private
     */
    public void buatPohon(){        
        kurakuraku.setJejak(false);
        kurakuraku.reset();
        kurakuraku.rotasi(90);
        kurakuraku.maju(100);
        kurakuraku.rotasi(180);
        buatPohon(6,50);        
        kurakuraku.reset();
    }
    
    private void buatPohon(int ukuran, int tinggi){
        if (ukuran>0){
            kurakuraku.setJejak(true);
            kurakuraku.maju(tinggi);                        
            kurakuraku.rotasi(-45);
            Dimension posAwal = kurakuraku.getPosition();
            double arah = kurakuraku.getArah();
            double sudut = arah;
            for(int i=0;i<3;i++){  
                buatPohon(ukuran-1,(int)(tinggi/1.5));
                kurakuraku.setJejak(false);
                kurakuraku.setPosition(posAwal);
                kurakuraku.setArah(arah);                
                sudut+=45;
                kurakuraku.rotasi(sudut);  
            }     
        }
        buatKotak(4);
        kurakuraku.reset();
    }
}
