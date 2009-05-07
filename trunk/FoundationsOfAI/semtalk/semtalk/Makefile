# ############################################################################
#   ,=³ .%%% %%%%%%%. .%%%%%.  .%%%%%.   %%%%%.%%. %. %%%%%%%% %% Rheinisch-
#  [==].%%%   %%   %% %%   %%  %%   %%   %%  %%'%%.%%.%% %% %% %% Westfälische
#   //l%%%    %%   %% %%%%. ' %%         %%%%%' '%%%%%%% %% %%%%% Technische
#  [==]%%     %%|=%%   %%=>%  %%         %%  %%  '%%'%%% %% %% %% Hochschule
#  [==]%%%    %%   %% . '%%%% %%  '%%%   %%   %%  '% '%% %% %% %% Aachen
#  [==]'%%%   %%   %% %%   %%  %%   %%
#  o^^o '%%% %%%%%%%' '%%%%%'O '%%%%%'    www-kbsg.informatik.rwth-aachen.de
#  Knowledge - Based  Systems   Group
# ############################################################################
#
#            $Id: Makefile,v 1.1.1.1 2006/12/22 10:00:13 stf Exp $
#    description: this is a generic Makefile for (La)TeX processing
#         author: Stefan Schiffer (based on stuff from Alexander Ferrein)
#
# ############################################################################

## VARIABLE(S) ############

SILENT = @

## MAIN ###################

PREFIX = semtalk

## FILES ##################

TEXFILE = $(PREFIX).tex
PSFILE  = $(PREFIX).ps
PDFFILE = $(PREFIX).pdf
DVIFILE = $(PREFIX).dvi
AUXFILE = $(PREFIX).aux
LOGFILE = $(PREFIX).log
BBLFILE = $(PREFIX).bbl
BLGFILE = $(PREFIX).blg
TARFILE = $(PREFIX).tgz

RMFILES = *~ *.adx *.and *.abb *.aux *.bbl *.blg *.idx *.ilg *.ind \
	  *.log *.lot *.lof *.ldx *.nav *.out *.snm *.tmp *.toc $(TARFILE)

## COMMANDS ###############

MAKE     = make -s
LATEX    = latex
BIBTEX   = bibtex
DVIPS    = dvips
DVIPSFLAGS = -Ppdf -G0 -j0 -Z 
PSTOPS   = pstops
DVIPDF   = dvipdf
PS2PDF   = ps2pdf
PS2PDFFLAGS =  -sPAPERSIZE=a4 -dCompatibilityLevel=1.3 -dEmbedAllFonts=true
PSNUP    = psnup
PDFLATEX = pdflatex
MAKEIDX  = makeindex
GV       = gv
GV_OPT   = -geometry 676x920-0+0
XPDF     = xpdf
XPDF_OPT = -geometry 676x920-0+0

## TOOLS ##################

RM       = rm -f
DBG      = echo
MSG      = echo


## COLORS #################

NORMAL      = tput sgr0
DARKGREY    = tput setaf 0; tput bold
RED         = tput setaf 1
LIGHTRED    = tput setaf 1; tput bold
GREEN       = tput setaf 2
LIME        = tput setaf 2; tput bold
BLUE        = tput setaf 4
BRIGHTBLUE  = tput setaf 4; tput bold
PURPLE      = tput setaf 5
PINK        = tput setaf 5; tput bold
CYAN        = tput setaf 6
BRIGHTCYAN  = tput setaf 6; tput bold
LIGHTGREY   = tput setaf 7
WHITE       = tput setaf 7; tput bold

## NAMED-HELPER ###########

MENU  = $(CYAN);
ITEM  = $(BRIGHTCYAN);
DONE  = $(CYAN);
CHECK = $(GREEN);
ERROR = $(RED);
RESET = ;$(NORMAL)

## RULES ##################

all:	dolatex \
	dodvips \
	dopdf

# ###############
#  normal latex

dolatex:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Compiling $(TEXFILE) ($(LATEX))" $(RESET)
	$(SILENT) $(LATEX) $(TEXFILE)
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)


dodvips:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Generating $(PSFILE) ($(DVIPS))" $(RESET)
	$(SILENT) $(DVIPS) $(DVIPSFLAGS) -o$(PSFILE) $(PREFIX)
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

dopdf:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Generating $(PDFFILE) ($(PS2PDF))" $(RESET)
	$(SILENT) $(PS2PDF) $(PSFILE) $(PDFFILE)
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

# ###############
#  pdftex-based

dopdftex:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Creating '$(PDFFILE)' via $(PDFLATEX)" $(RESET)
	$(SILENT) $(PDFLATEX) $(TEXFILE)
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

# ###############
#  multipage

do2up:	
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Generating 2up version ($(PSNUP))" $(RESET)
	$(SILENT) $(PSNUP) -2 $(PSFILE) $(PREFIX).2up.ps
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

do4up:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Generating 4up version ($(PSNUP))" $(RESET)
	$(SILENT) $(PSNUP) -4 $(PSFILE) $(PREFIX).4up.ps
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

draft:	
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Generating A4 draft ($(PSTOPS))" $(RESET)
	$(SILENT) $(PSTOPS) -h1189mm -w841mm "1:0@.24" < $(PSFILE) > $(PREFIX).a4.ps
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

# ###############
# view and show

viewps:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- View $(PSFILE) with $(GV)" $(RESET)
	$(SILENT) $(GV) $(GV_OPT) $(PSFILE) &
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

viewpdf:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- View $(PDFFILE) with $(XPDF)" $(RESET)
	$(SILENT) $(XPDF) $(XPDF_OPT) $(PDFFILE) &
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

show:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Show $(PDFFILE) with $(XPDF)" $(RESET)
	$(SILENT) $(XPDF) -fullscreen $(PDFFILE) &
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)

# ###############
# clean up

clean:
	$(SILENT) $(MENU) $(MSG) " ---------------------------------------------------------------------- " $(RESET)
	$(SILENT) $(ITEM) $(MSG) "  -- Cleaning up ..."
	$(SILENT) $(RM) $(RMFILES)
	$(SILENT) $(DONE) $(MSG) " ------------------------------------------------------------- done. -- " $(RESET)
