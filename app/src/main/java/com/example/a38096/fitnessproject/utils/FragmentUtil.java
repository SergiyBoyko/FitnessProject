package com.example.a38096.fitnessproject.utils;

import androidx.annotation.IdRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a38096.fitnessproject.R;

public class FragmentUtil {

    public static void setFragment(FragmentManager fragmentManager,
                                   Fragment fragment,
                                   @IdRes int rootContainer) {
        changeFragment(fragmentManager, fragment, false, false, rootContainer);
    }

    /**
     * Change the current displayed fragment by a new one.
     * - if the fragment is in back stack, it will pop it
     * - if the fragment is already displayed (trying to change the fragment with the same), it will not do anything
     *
     * @param fragment        the new fragment display
     * @param saveInBackStack if we want the fragment to be in back stack
     * @param animate         if we want a nice animation or not
     */
    public static void changeFragment(FragmentManager fragmentManager, Fragment fragment, boolean saveInBackStack, boolean animate, @IdRes int rootContainer) {
        String backStateName = fragment.getClass().getName();
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (animate) {
                transaction.setCustomAnimations(R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right);
            }
            transaction.replace(rootContainer, fragment, backStateName);
            if (saveInBackStack) {
                transaction.addToBackStack(backStateName);
            }

            transaction.commitAllowingStateLoss();

        } catch (IllegalStateException e) {
            e.printStackTrace();
            AndroidUtils.showLongToast(fragment.getContext(), e.getMessage());
        }
    }

    /**
     * Change the current displayed fragment by a new one.
     * - if the fragmebt is in backstack, it will pop it
     * - if the fragment is already displayed (trying to change the fragment with the same), it will not do anything
     *
     * @param fragment        the new fragment display
     * @param saveInBackstack if we want the fragment to be in backstack
     * @param animate         if we want a nice animation or not
     */
    public static void changeFragmentWithOld(FragmentManager fragmentManager, Fragment fragment, boolean saveInBackstack, boolean animate, @IdRes int rootContainer) {
        String backStateName = fragment.getClass().getName();
        try {
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
            if (!fragmentPopped) {//fragment not in back stack, create it.
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (animate) {
                    transaction.setCustomAnimations(R.anim.enter_from_right,
                            R.anim.exit_to_left,
                            R.anim.enter_from_left,
                            R.anim.exit_to_right);
                }
                transaction.replace(rootContainer, fragment, backStateName);
                if (saveInBackstack) {
                    transaction.addToBackStack(backStateName);
                }
                transaction.commit();
            }


        } catch (IllegalStateException e) {
            e.printStackTrace();
            AndroidUtils.showLongToast(fragment.getContext(), e.getMessage());
        }
    }


    /**
     * Replace the current displayed fragment by a new one.
     * - if the fragmebt is in backstack, it will pop it
     * - if the fragment is already displayed (trying to change the fragment with the same), it will not do anything
     *
     * @param fragment        the new fragment display
     * @param saveInBackstack if we want the fragment to be in backstack
     * @param animate         if we want a nice animation or not
     */
    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean saveInBackstack, boolean animate, @IdRes int rootContainer) {
        String backStateName = fragment.getClass().getName();
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (animate) {
                transaction.setCustomAnimations(R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right);
            }
            transaction.replace(rootContainer, fragment, backStateName);
            if (saveInBackstack) {
                transaction.addToBackStack(backStateName);
            }

            transaction.commitAllowingStateLoss();

        } catch (IllegalStateException e) {
            e.printStackTrace();
            AndroidUtils.showLongToast(fragment.getContext(), e.getMessage());
        }
    }

    /**
     * Add a new fragment above the current displayed fragment
     * - if the fragmebt is in backstack, it will pop it
     * - if the fragment is already displayed (trying to change the fragment with the same), it will not do anything
     *
     * @param fragment        the new fragment display
     * @param saveInBackstack if we want the fragment to be in backstack
     * @param animate         if we want a nice animation or not
     */
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, boolean saveInBackstack, boolean animate, @IdRes int rootContainer) {
        String backStateName = fragment.getClass().getName();
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (animate) {
                transaction.setCustomAnimations(R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right);
            }
            transaction.add(rootContainer, fragment, backStateName);
            if (saveInBackstack) {
                transaction.addToBackStack(backStateName);
            }

            transaction.commit();

        } catch (IllegalStateException e) {
            e.printStackTrace();
            AndroidUtils.showLongToast(fragment.getContext(), e.getMessage());
        }
    }

    public static void showDialog(FragmentManager fragmentManager, DialogFragment dialogFragment, boolean saveInBackstack) {
        String backStateName = dialogFragment.getClass().getName();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (saveInBackstack) {
            ft.addToBackStack(backStateName);
        }
        dialogFragment.show(fragmentManager, backStateName);
    }

}