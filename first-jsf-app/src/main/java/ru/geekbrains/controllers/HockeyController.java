package ru.geekbrains.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.persist.HockeyScoreBoard;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class HockeyController implements Serializable {

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Getter
    @Setter
    HockeyScoreBoard hockeyScoreBoard;

    @Getter
    @Setter
    String ss = "Start";

    Long oldTime, newTime;

    @Getter
    @Setter
    boolean countdown = true;


    public String createBoard() {
        hockeyScoreBoard = new HockeyScoreBoard();
        return "/hockey_form.xhtml?faces-redirect=true";
    }

    @Transactional
    public String saveOrUpdateBoard() {
        if (hockeyScoreBoard.getId() == null) entityManager.persist(hockeyScoreBoard);
        entityManager.merge(hockeyScoreBoard);
        return "/my_boards.xhtml?faces-redirect=true";
    }

    public String openBoard(HockeyScoreBoard hockeyScoreBoard) {
        this.hockeyScoreBoard = hockeyScoreBoard;
        return "/admin_board.xhtml?faces-redirect=true";
    }

    public String broadcast(HockeyScoreBoard hockeyScoreBoard) {
        this.hockeyScoreBoard = hockeyScoreBoard;
        return "/broadcast.xhtml?faces-redirect=true";
    }

    @Transactional
    public String editBoard(HockeyScoreBoard hockeyScoreBoard) {
        this.hockeyScoreBoard = hockeyScoreBoard;
        return "/hockey_form.xhtml?faces-redirect=true";
    }

    @Transactional
    public void deleteBoard(Long id) {
        entityManager.createQuery("DELETE FROM HockeyScoreBoard h WHERE h.id = :id").setParameter("id", id).executeUpdate();
    }

    public HockeyScoreBoard findById(Long id) {
        return entityManager.find(HockeyScoreBoard.class, id);
    }

    public List<HockeyScoreBoard> getAllBoards() {
        return entityManager.createQuery("SELECT h FROM HockeyScoreBoard h", HockeyScoreBoard.class).getResultList();
    }

//  HOME ---------------------------------
    @Transactional
    public void plusOneHomeScore(int i) {
        hockeyScoreBoard.setHomeScore(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneHomeScore(int i) {
        hockeyScoreBoard.setHomeScore(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOneHomePenaltyMinutes1(int i) {
        hockeyScoreBoard.setHomePenaltyMinutes1(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneHomePenaltyMinutes1(int i) {
        hockeyScoreBoard.setHomePenaltyMinutes1(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOneHomePenaltySeconds1(int i, String s) {
        hockeyScoreBoard.setHomePenaltySeconds1(i+1);
//        hockeyScoreBoard.setHomePenaltyNumber1(s);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneHomePenaltySeconds1(int i) {
        hockeyScoreBoard.setHomePenaltySeconds1(i-1);
        saveOrUpdateBoard();
    }
//  HOME END -------------------------------------------

    @Transactional
    public void plusOneAwayScore(int i) {
        hockeyScoreBoard.setAwayScore(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneAwayScore(int i) {
        hockeyScoreBoard.setAwayScore(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOneAwayPenaltyMinutes1(int i) {
        hockeyScoreBoard.setAwayPenaltyMinutes1(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneAwayPenaltyMinutes1(int i) {
        hockeyScoreBoard.setAwayPenaltyMinutes1(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOneAwayPenaltySeconds1(int i) {
        hockeyScoreBoard.setAwayPenaltySeconds1(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneAwayPenaltySeconds1(int i) {
        hockeyScoreBoard.setAwayPenaltySeconds1(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOneMinute(int i) {
        hockeyScoreBoard.setMinutes(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneMinute(int i) {
        hockeyScoreBoard.setMinutes(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOneSecond(int i) {
        hockeyScoreBoard.setSeconds(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOneSecond(int i) {
        hockeyScoreBoard.setSeconds(i-1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void plusOnePeriod(int i) {
        hockeyScoreBoard.setPeriod(i+1);
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOnePeriod(int i) {
        hockeyScoreBoard.setPeriod(i-1);
        saveOrUpdateBoard();
    }

    Thread forwardThread = new Thread(() -> {
        while (ss.equals("Stop")) {
            try {
                Thread.sleep(1000);
                hockeyScoreBoard.setSeconds(hockeyScoreBoard.getSeconds() + 1);

                if (hockeyScoreBoard.getSeconds() == 60) {
                    hockeyScoreBoard.setMinutes(hockeyScoreBoard.getMinutes() + 1);
                    hockeyScoreBoard.setSeconds(0);
                }
                checkPenaltyTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Thread countdownThread = new Thread(() -> {
        while (ss.equals("Stop")) {
            try {
                Thread.sleep(1000);
                hockeyScoreBoard.setSeconds(hockeyScoreBoard.getSeconds() - 1);

                if (hockeyScoreBoard.getSeconds() == 60) {
                    hockeyScoreBoard.setMinutes(hockeyScoreBoard.getMinutes() - 1);
                    hockeyScoreBoard.setSeconds(0);
                }
                checkPenaltyTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    @Transactional
    public void checkPenaltyTime(){
        if (hockeyScoreBoard.getHomePenaltyNumber1() != null){
            hockeyScoreBoard.setHomePenaltySeconds1(hockeyScoreBoard.getHomePenaltySeconds1() - 1);

            if (hockeyScoreBoard.getHomePenaltySeconds1() == 0 && hockeyScoreBoard.getHomePenaltyMinutes1() > 0) {
                hockeyScoreBoard.setHomePenaltyMinutes1(hockeyScoreBoard.getHomePenaltyMinutes1() - 1);
                hockeyScoreBoard.setHomePenaltySeconds1(59);
            }
            else if (hockeyScoreBoard.getHomePenaltySeconds1() == 0 && hockeyScoreBoard.getHomePenaltyMinutes1() == 0){
                hockeyScoreBoard.setHomePenaltyNumber1(null);
                saveOrUpdateBoard();
            }
        }
    }

    @Transactional
    public void forwardTime() {
        if (ss.equals("Start")) {
            ss = "Stop";
            saveOrUpdateBoard();

            if (countdown){
                if (!countdownThread.isAlive()){
                    countdownThread.setDaemon(true);
                    countdownThread.start();
                } else countdownThread.resume();
            } else {
                if (!forwardThread.isAlive()){
                    forwardThread.setDaemon(true);
                    forwardThread.start();
                } else forwardThread.resume();
            }
        } else if (ss.equals("Stop")){
            ss = "Start";
            saveOrUpdateBoard();
            if (countdown) countdownThread.suspend();
            else forwardThread.suspend();
        }
    }
}
