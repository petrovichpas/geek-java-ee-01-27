package ru.geekbrains.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.persist.HockeyScoreBoard;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Getter
    @Setter
    List<Integer> times = new ArrayList(Arrays.asList(20, 15, 10, 0));
//    List<String> times = new ArrayList(Arrays.asList("20:00", "15:00", "10:00", "00:00"));

    @Getter
    @Setter
    boolean countdown = false;


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

    @Transactional
    public void plusOne(ActionEvent event) {
        switch (event.getComponent().getId()){
            case "homeScore--1": hockeyScoreBoard.setHomeScore(hockeyScoreBoard.getHomeScore() + 1);
                break;
            case "awayScore--1": hockeyScoreBoard.setAwayScore(hockeyScoreBoard.getAwayScore() + 1);
                break;
            case "minutes--1": hockeyScoreBoard.setMinutes(hockeyScoreBoard.getMinutes() + 1);
                break;
            case "seconds--1": hockeyScoreBoard.setSeconds(hockeyScoreBoard.getSeconds() + 1);
                break;
            case "period--1": hockeyScoreBoard.setPeriod(hockeyScoreBoard.getPeriod() + 1);
                break;
            case "homePenaltyMinutes1--1": hockeyScoreBoard.setHomePenaltyMinutes1(hockeyScoreBoard.getHomePenaltyMinutes1() + 1);
                break;
            case "homePenaltyMinutes2--1": hockeyScoreBoard.setHomePenaltyMinutes2(hockeyScoreBoard.getHomePenaltyMinutes2() + 1);
                break;
            case "homePenaltyMinutes3--1": hockeyScoreBoard.setHomePenaltyMinutes3(hockeyScoreBoard.getHomePenaltyMinutes3() + 1);
                break;
            case "homePenaltySeconds1--1": hockeyScoreBoard.setHomePenaltySeconds1(hockeyScoreBoard.getHomePenaltySeconds1() + 1);
                break;
            case "homePenaltySeconds2--1": hockeyScoreBoard.setHomePenaltySeconds2(hockeyScoreBoard.getHomePenaltySeconds2() + 1);
                break;
            case "homePenaltySeconds3--1": hockeyScoreBoard.setHomePenaltySeconds3(hockeyScoreBoard.getHomePenaltySeconds3() + 1);
                break;
            case "awayPenaltyMinutes1--1": hockeyScoreBoard.setAwayPenaltyMinutes1(hockeyScoreBoard.getAwayPenaltyMinutes1() + 1);
                break;
            case "awayPenaltyMinutes2--1": hockeyScoreBoard.setAwayPenaltyMinutes2(hockeyScoreBoard.getAwayPenaltyMinutes2() + 1);
                break;
            case "awayPenaltyMinutes3--1": hockeyScoreBoard.setAwayPenaltyMinutes3(hockeyScoreBoard.getAwayPenaltyMinutes3() + 1);
                break;
            case "awayPenaltySeconds1--1": hockeyScoreBoard.setAwayPenaltySeconds1(hockeyScoreBoard.getAwayPenaltySeconds1() + 1);
                break;
            case "awayPenaltySeconds2--1": hockeyScoreBoard.setAwayPenaltySeconds2(hockeyScoreBoard.getAwayPenaltySeconds2() + 1);
                break;
            case "awayPenaltySeconds3--1": hockeyScoreBoard.setAwayPenaltySeconds3(hockeyScoreBoard.getAwayPenaltySeconds3() + 1);
                break;
        }
        saveOrUpdateBoard();
    }

    @Transactional
    public void minusOne(ActionEvent event) {
        switch (event.getComponent().getId()){
            case "homeScore-1": hockeyScoreBoard.setHomeScore(hockeyScoreBoard.getHomeScore() - 1);
                break;
            case "awayScore-1": hockeyScoreBoard.setAwayScore(hockeyScoreBoard.getAwayScore() - 1);
                break;
            case "minutes-1": hockeyScoreBoard.setMinutes(hockeyScoreBoard.getMinutes() - 1);
                break;
            case "seconds-1": hockeyScoreBoard.setSeconds(hockeyScoreBoard.getSeconds() - 1);
                break;
            case "period-1": hockeyScoreBoard.setPeriod(hockeyScoreBoard.getPeriod() - 1);
                break;
            case "homePenaltyMinutes1-1": hockeyScoreBoard.setHomePenaltyMinutes1(hockeyScoreBoard.getHomePenaltyMinutes1() - 1);
                break;
            case "homePenaltyMinutes2-1": hockeyScoreBoard.setHomePenaltyMinutes2(hockeyScoreBoard.getHomePenaltyMinutes2() - 1);
                break;
            case "homePenaltyMinutes3-1": hockeyScoreBoard.setHomePenaltyMinutes3(hockeyScoreBoard.getHomePenaltyMinutes3() - 1);
                break;
            case "homePenaltySeconds1-1": hockeyScoreBoard.setHomePenaltySeconds1(hockeyScoreBoard.getHomePenaltySeconds1() - 1);
                break;
            case "homePenaltySeconds2-1": hockeyScoreBoard.setHomePenaltySeconds2(hockeyScoreBoard.getHomePenaltySeconds2() - 1);
                break;
            case "homePenaltySeconds3-1": hockeyScoreBoard.setHomePenaltySeconds3(hockeyScoreBoard.getHomePenaltySeconds3() - 1);
                break;
            case "awayPenaltyMinutes1-1": hockeyScoreBoard.setAwayPenaltyMinutes1(hockeyScoreBoard.getAwayPenaltyMinutes1() - 1);
                break;
            case "awayPenaltyMinutes2-1": hockeyScoreBoard.setAwayPenaltyMinutes2(hockeyScoreBoard.getAwayPenaltyMinutes2() - 1);
                break;
            case "awayPenaltyMinutes3-1": hockeyScoreBoard.setAwayPenaltyMinutes3(hockeyScoreBoard.getAwayPenaltyMinutes3() - 1);
                break;
            case "awayPenaltySeconds1-1": hockeyScoreBoard.setAwayPenaltySeconds1(hockeyScoreBoard.getAwayPenaltySeconds1() - 1);
                break;
            case "awayPenaltySeconds2-1": hockeyScoreBoard.setAwayPenaltySeconds2(hockeyScoreBoard.getAwayPenaltySeconds2() - 1);
                break;
            case "awayPenaltySeconds3-1": hockeyScoreBoard.setAwayPenaltySeconds3(hockeyScoreBoard.getAwayPenaltySeconds3() - 1);
                break;
        }
        saveOrUpdateBoard();
    }

    Thread forwardThread = new Thread(() -> {
        while (ss.equals("Stop")) {
            try {
                Thread.sleep(1000);
                hockeyScoreBoard.setSeconds(hockeyScoreBoard.getSeconds() + 1);
                checkPenaltyTime();

                if (hockeyScoreBoard.getSeconds() == 60) {
                    hockeyScoreBoard.setMinutes(hockeyScoreBoard.getMinutes() + 1);
                    hockeyScoreBoard.setSeconds(0);
                }
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
                checkPenaltyTime();

                if (hockeyScoreBoard.getSeconds() == 0) {
                    hockeyScoreBoard.setMinutes(hockeyScoreBoard.getMinutes() - 1);
                    hockeyScoreBoard.setSeconds(59);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public void checkPenaltyTime(){
        if (!hockeyScoreBoard.getHomePenaltyNumber1().equals("")) {
            hockeyScoreBoard.setHomePenaltySeconds1(hockeyScoreBoard.getHomePenaltySeconds1() - 1);

            if (hockeyScoreBoard.getHomePenaltyMinutes1() > 0 && hockeyScoreBoard.getHomePenaltySeconds1() < 0) {
                hockeyScoreBoard.setHomePenaltyMinutes1(hockeyScoreBoard.getHomePenaltyMinutes1() - 1);
                hockeyScoreBoard.setHomePenaltySeconds1(59);
            } else if (hockeyScoreBoard.getHomePenaltyMinutes1() == 0 && hockeyScoreBoard.getHomePenaltySeconds1() <= 0){
                hockeyScoreBoard.setHomePenaltyNumber1("");
            }
        }


//            if (hockeyScoreBoard.getHomePenaltySeconds1() == 0 && hockeyScoreBoard.getHomePenaltyNumber1() != null) {
//                hockeyScoreBoard.setHomePenaltyMinutes1(hockeyScoreBoard.getHomePenaltyMinutes1() - 1);
//                hockeyScoreBoard.setHomePenaltySeconds1(59);
//            } else {
//                hockeyScoreBoard.setHomePenaltySeconds1(hockeyScoreBoard.getHomePenaltySeconds1() - 1);
//            }
//
//        if (hockeyScoreBoard.getHomePenaltyMinutes1() == 0 && hockeyScoreBoard.getHomePenaltySeconds1() == 0) {
//            hockeyScoreBoard.setHomePenaltyNumber1(null);
//        }
    }

    @Transactional
    public void start() {
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
