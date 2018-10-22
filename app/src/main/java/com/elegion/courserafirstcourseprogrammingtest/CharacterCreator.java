package com.elegion.courserafirstcourseprogrammingtest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

public class CharacterCreator extends Observable  implements Serializable{

    public enum Specialization {
        WARRIOR, ARCHER, MAGE;

        private static Specialization[] vals = values();

        public static String[] getNames() {
            LinkedList<String> list = new LinkedList<>();
            for (Specialization s : vals) {
                list.add(s.name().substring(0, 1) + s.name().substring(1).toLowerCase());
            }
            return list.toArray(new String[list.size()]);
        }

        public static Specialization getLast()
        {
            return vals[vals.length - 1];
        }

        public static Specialization getFirst()
        {
            return vals[0];
        }
    }

    public enum Race {
        HUMAN, ELF, ORC, DWARF;

        private static Race[] vals = values();

        public static String[] getNames() {
            LinkedList<String> list = new LinkedList<>();
            for (Race r : vals) {
                list.add(r.name().substring(0, 1) + r.name().substring(1).toLowerCase());
            }
            return list.toArray(new String[list.size()]);
        }

        public static Race getLast()
        {
            return vals[vals.length - 1];
        }

        public static Race getFirst()
        {
            return vals[0];
        }
    }

    public enum Attribute {
        STRENGTH, AGILITY, INTELLECT, STAMINA, LUCK;

        private static Attribute[] vals = values();

        public static String[] getNames() {
            LinkedList<String> list = new LinkedList<>();
            for (Attribute a : vals) {
                list.add(a.name().substring(0, 1) + a.name().substring(1).toLowerCase());
            }
            return list.toArray(new String[list.size()]);
        }

        public static Attribute getByPosition(int position) {
            int i = 0;
            for (Attribute a : vals) {
                if (i == position) {
                    return a;
                }
                i++;
            }
            return null;
        }
    }

    public enum Perk {
        BERSERK, CALM, LIGHTWEIGHT, HEAVYARMORED, OBSERVANT, MEDITATIONS;

        public static String[] getNames() {
            LinkedList<String> list = new LinkedList<>();
            for (Perk p : values()) {
                list.add(p.name().substring(0, 1) + p.name().substring(1).toLowerCase());
            }
            return list.toArray(new String[list.size()]);
        }
    }

    private String mName;
    private Specialization mSpecialization;
    private Race mRace;
    private int mAvailablePoints;

    private Map<String, Integer> mAttributesMap = new HashMap<>();
    private Map<String, Boolean> mPerksMap = new HashMap<>();


    public CharacterCreator() {
        mRace = Race.HUMAN;
        mSpecialization = Specialization.WARRIOR;
        mAvailablePoints = 5;
        mAttributesMap.put(Attribute.STRENGTH.name(), 5);
        mAttributesMap.put(Attribute.AGILITY.name(), 5);
        mAttributesMap.put(Attribute.INTELLECT.name(), 5);
        mAttributesMap.put(Attribute.STAMINA.name(), 5);
        mAttributesMap.put(Attribute.LUCK.name(), 5);
    }


    public String[] getSpecializations() {
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Specialization
        *   Строки должны начинаться с заглавной буквы, остальные строчные
        * */

        //return new String[]{""};
        return Specialization.getNames();
    }

    public void setSpecialization(int position) {
        /*
        *  этот метод задает специализацию в переменную mSpecialization
        *  на вход подается число, и из enum Specialization выбирается соответствующий класс
        *  0 - Warrior
        *  1 - Archer
        *  2 - Mage
        *  если введенное число меньше 0, то в mSpecialization записывается самое первое (порядковый номер - 0) значение
        *  если введенное число больше длины enum, то в mSpecialization записывается самое последнее (длина - 1) значение
        *
        * */
        if (position < 0) {
            mSpecialization = Specialization.getFirst();
        } else if (position > Specialization.vals.length - 1) {
            mSpecialization = Specialization.getLast();
        } else {
            for (Specialization spec: Specialization.vals) {
                if (spec.ordinal() == position) {
                    mSpecialization = spec;
                }
            }
        }
    }

    public String[] getRaces() {
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Races
        *    Строка должна быть формата - первая буква заглавная, остальные строчные
        *   One, Two, Three
        * */
        //return new String[]{""};
        return Race.getNames();
    }

    public void setRace(int position) {
        /*
        *  этот метод задает специализацию в переменную mRace
        *  на вход подается число, и из enum Race выбирается соответствующая раса
        *  0 - Human
        *  1 - Elf
        *  2 - Orc
        *  3 - Dwarf
        *  если введенное число меньше 0, то в mRace записывается самое первое (порядковый номер - 0) значение
        *  если введенное число больше длины enum, то в mRace записывается самое последнее (длина - 1) значение
        *
        * */
        if (position < 0) {
            mRace = Race.getFirst();
        } else if (position > Race.vals.length - 1) {
            mRace = Race.getLast();
        } else {
            for (Race race: Race.vals) {
                if (race.ordinal() == position) {
                    mRace = race;
                }
            }
        }
    }

    public String[] getAttributes() {
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Attribute
        *    Строка должна быть формата - первая буква заглавная, остальные строчные
        *   One, Two, Three
        * */
        //return new String[]{""};
        return Attribute.getNames();
    }

    public String[] getPerks() {
        /*
        *   этот метод должен возвращать массив строк, созданных на основе enum Perk
        *   Строка должна быть формата - первая буква заглавная, остальные строчные
        *   One, Two, Three
        *
        * */
        return Perk.getNames();
    }

    public void updateAttributeValue(int position, int updateTo) {
        // 11.12.2017
        /*
        *  этот метод увеличивает/уменьшает соответствующее значение атрибута
        *  рекомендуется реализовывать его в последнюю очередь
        *
        * 1. на вход подается позиция изменяемого атрибута и на сколько нужно этот атрибут увеличить/уменьшить
        * 2. по позиции атрибута выявляется название атрибута из enum Attribute
        * 3. по названию атрибута получается значение атрибута из mAttributesMap
        * 4. если атрибут собирается увеличиться и у персонажа достаточно очков для увеличения атрибута (mAvailablePoints)
        *    или
        *    если атрибут собирается уменьшиться и уменьшенный атрибут будет больше 0,
        *    то атрибут изменяется, количество доступных очков меняется в противоположную сторону.
        *
        * 5. убедитесь в том, что измененное значение атрибута записано в mAttributesMap
        * 6. убедитесь в том, что измененное значение количества очков записано в mAvailablePoints;
        * 7. после изменения нужно вызвать методы setChanged(); notifyObservers();
        *    для того, чтобы изменения отразились на верстке
        *
        * пример работы алгоритма.
        * на вход подается (0, -1)
        * из значения 0 выясняем, что меняться будет атрибут STRENGTH
        * получаем текущее значение этого атрибута из mAttributesMap
        * допустим, оно равно 3
        * по условию все алгоритма все проходит
        * сила уменьшается до 2, количество доступных очков увеличивается на +1
        *
        * если STRENGTH равно 1, то ничего не происходит,
        * так как мы не можем уменьшить атрибут ниже 1
        *
        * если на вход пришло (0, 1)
        *   если доступных очков больше 0,
        *       то STRENGTH увеличивается на 1, количество доступных очков уменьшается на 1
        *   если количество доступных очков равно 0
        *       то мы не можем увеличить атрибут, ничего не происходит        *
        * */

        // Нет доступных очков для увеличения атрибута
        if (mAvailablePoints - updateTo < 0) {
            // TODO Выбросить исключение
            return;
        }

        Integer attributeValue = getAttributesMap().get(Attribute.getByPosition(position).name());
        // Атрибут не может быть менее 1
        if (attributeValue + updateTo < 1) {
            // TODO Выбросить исключение
            return;
        }

        mAttributesMap.put(Attribute.getByPosition(position).name(), attributeValue + updateTo);
        setAvailablePoints(mAvailablePoints - updateTo);
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvailablePoints() {
        return String.valueOf(mAvailablePoints);
    }

    public Map<String, Integer> getAttributesMap() {
        return mAttributesMap;
    }

    public void checkPerk(String text, boolean isChecked) {
        mPerksMap.put(text, isChecked);
    }

    public Character create() {
        Character character = new Character();
        character.setName(mName);
        character.setRace(mRace);
        character.setSpecialization(mSpecialization);
        character.setAttributes(mAttributesMap);
        character.setPerks(mPerksMap);
        character.calculateParameters();
        return character;
    }

    public Specialization getSpecialization() {
        return mSpecialization;
    }

    public Race getRace() {
        return mRace;
    }

    public Map<String, Boolean> getPerksMap() {
        return mPerksMap;
    }

    public void setAvailablePoints(int availablePoints) {
        mAvailablePoints = availablePoints;
    }

    public int getRacePosition() {
        return mRace.ordinal();
    }

    public int getSpecializationPosition() {
        return mSpecialization.ordinal();
    }
}
