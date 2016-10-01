/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesearcher;

import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Этот класс предназначен для поиска файлов
 *
 * @author IvanP_000
 */
public class Searcher {

    public File dir;
    public Pattern p;

    Searcher() {
        dir = new File(System.getProperty("user.dir"));
    }

    /**
     * Этот метод выполняет поиск файлов в рабочей директории программы. Для
     * поиска во вложенных папках используется рекурсия.
     *
     * @param file - имя (регулярное выражение/часть имени) файла для поиска
     * @param dir - директория, в которой осуществляется поиск
     * @param date - определяет, показывать ли дату изменения файла и размер.
     */
    public void search(String file, File dir, boolean date) {
        File[] files;
        // получаем список файлов в директории
        files = dir.listFiles();
        for (File file1 : files) {
            Matcher m = p.matcher(file1.getName());
            // если это файл - проверяем на соответствие запросу
            if (file1.isFile()) {
                if (m.matches() || file1.getName().contains(file)) {
                    // проверяем, выводить ли доп. параметры
                    if (date) {
                        System.out.printf("file: %-29s %-85s %-15s %7s%n", file1.getName(), file1.getParent(),
                                new Date(file1.lastModified()), file1.length());
                    } else {

                        System.out.printf("file: %-29s %10s%n", file1.getName(), file1.getParent());
                    }
                }
                // если директория, то вызываем функцию для поиска в новой папке
            } else if (file1.isDirectory()) {
                if (m.matches() || file1.getName().contains(file)) {
                    if (date) {
                        System.out.printf("dir: %-30s %-85s %-15s %7s%n", file1.getName(), file1.getParent(),
                                new Date(file1.lastModified()), file1.length());
                        search(file, file1, date);
                    } else {
                        System.out.printf("dir: %-30s %10s%n", file1.getName(), file1.getParent());
                        search(file, file1, date);
                    }
                } else {
                    search(file, file1, date);

                }
            }
        }
    }
}
