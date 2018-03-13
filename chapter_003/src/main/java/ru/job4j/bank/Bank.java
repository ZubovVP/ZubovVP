package ru.job4j.bank;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    private Map<User, List<Account>> mapUser = new HashMap<>();

    public Map<User, List<Account>> getMapUser() {
        return mapUser;
    }

    /**
     * Add an user to the collection.
     * @param user - User.
     */
    public void addUser(User user) {
        mapUser.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Remove an user from the collection.
     * @param user - User.
     */
    public void deleteUser(User user) {
        mapUser.remove(user);
    }

    /**
     * Add an account for the user in the collection.
     * @param passport - User's passport.
     * @param account - User's account.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> entry : mapUser.entrySet()) {
            if (entry.getKey().getPasport().equals(passport)) {
                entry.getValue().add(account);
            }
        }
    }

    /**
     * Remove an account for the user in the collection.
     * @param passport - User's passport.
     * @param account - User's account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> entry : mapUser.entrySet()) {
            if (entry.getKey().getPasport().equals(passport)) {
                entry.getValue().remove(account);
            }
        }
    }

    /**
     * Get account's collection from user.
     * @param passport - Users's passport.
     * @return - Account's collection
     * @throws FileNotFoundException
     */
    public List<Account> getAccounts(String passport) throws FileNotFoundException {
        List<Account> result = null;
        for (Map.Entry<User, List<Account>> entry : mapUser.entrySet()) {
          if (entry.getKey().getPasport().equals(passport)) {
              result = entry.getValue();
          }
          if (result == null) {
              throw  new FileNotFoundException("This user doesn't have account");
          }
        }
        return result;
    }

    /**
     * Transfer of maney from one account to another.
     * @param srcPassport - first user pasport.
     * @param srcRequisite - first requisite account.
     * @param destPassport - second user pasport.
     * @param dstRequisite - second requisite account.
     * @param amount - amount's money.
     * @return - result of transfer.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        Account src = null;
        Account dest = null;
        boolean result = false;
        for (Map.Entry<User, List<Account>> entry : mapUser.entrySet()) {
            if (entry.getKey().getPasport().equals(srcPassport)) {
                for (Account result1 : entry.getValue()) {
                    if (result1.getRequisites().equals(srcRequisite)) {
                        src = result1;
                    }
                }
            } else if (entry.getKey().getPasport().equals(destPassport)) {
                for (Account result2 : entry.getValue()) {
                    if (result2.getRequisites().equals(dstRequisite)) {
                        dest = result2;
                    }
                }
            }
            if (src != null && dest != null && src.getValue() > amount) {
                int number = (int) (src.getValue() - amount);
                src.setValue(number);
                int number1 = (int) (dest.getValue() + amount);
                dest.setValue(number1);
                result = true;
            }
        }
        return result;
    }
}