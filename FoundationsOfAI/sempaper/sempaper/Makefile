# ############################################################################
#   ,=³ .%%% %%%%%%%. .%%%%%.  .%%%%%.   %%%%%.%%. %. %%%%%%%% %% Rheinisch-
#  [==].%%%   %%   %% %%   %%  %%   %%   %%  %%'%%.%%.%% %% %% %% Westfälische
#   //l%%%    %%   %% %%%%. ' %%       @ %%%%%' '%%%%%%% %% %%%%% Technische
#  [==]%%     %%|=%%   %%=>%  %%         %%  %%  '%%'%%% %% %% %% Hochschule
#  [==]%%%    %%   %% . '%%%% %%  '%%%   %%   %%  '% '%% %% %% %% Aachen
#  [==]'%%%   %%   %% %%   %%  %%   %%
#  o^^o '%%% %%%%%%%' '%%%%%'O '%%%%%'    www-kbsg.informatik.rwth-aachen.de
#  Knowledge  Based   Systems   Group
# ############################################################################
#
#            $Id: Makefile,v 1.3 2008/03/07 11:38:10 stf Exp $
#         author: Stefan Schiffer
#    description: standard LaTeX-paper Makefile
#  last modified: $Date: 2008/03/07 11:38:10 $    by: $Author: stf $
#
# ############################################################################

PREFIX = sempaper

## #################################### ##
##  VARIABLES                           ##
## #################################### ##

## FILES ##################

TEXFILE = $(PREFIX).tex
BIBFILE = $(PREFIX).bib
DVIFILE = $(PREFIX).dvi
PSFILE  = $(PREFIX).ps
PDFFILE = $(PREFIX).pdf

RMFILES = *~ *.toc *.idx *.ilg *.ind *.bbl *.blg *.out *.aux \
	  *.tmp *.log *.lot *.lof *.adx *.and *.abb *.ldx 

## OPTIONS ################

SILENT = @

## COMMANDS ###############

MAKE     = make -s
LATEX    = latex
BIBTEX   = bibtex
DVIPS    = dvips
DVIPSFLAGS = -Ppdf -G0
DVIPDF   = dvipdf
PS2PDF   = ps2pdf
PS2PDFFLAGS =  -sPAPERSIZE=a4 -dCompatibilityLevel=1.3 -dEmbedAllFonts=true
PSNUP    = psnup
PDFLATEX = pdflatex
MAKEIDX  = makeindex

## TOOLS ##################

RM       = rm
RMF      = rm -f
RMRF     = rm -rf
DBG      = echo
MSG      = echo

## COLORS #################

RESET       = tput sgr0
#
BLACK       = tput setaf 0
BLACK_BG    = tput setab 0
DARKGREY    = tput setaf 0; tput bold
RED         = tput setaf 1
RED_BG      = tput setab 1
LIGHTRED    = tput setaf 1; tput bold
GREEN       = tput setaf 2
GREEN_BG    = tput setab 2
LIME        = tput setaf 2; tput bold
BROWN       = tput setaf 3
BROWN_BG    = tput setab 3
YELLOW      = tput setaf 3; tput bold
BLUE        = tput setaf 4
BLUE_BG     = tput setab 4
BRIGHTBLUE  = tput setaf 4; tput bold
PURPLE      = tput setaf 5
PURPLE_BG   = tput setab 5
PINK        = tput setaf 5; tput bold
CYAN        = tput setaf 6
CYAN_BG     = tput setab 6
BRIGHTCYAN  = tput setaf 6; tput bold
LIGHTGREY   = tput setaf 7
LIGHTGREYBG = tput setab 7
WHITE       = tput setaf 7; tput bold
#
# AND NOW FOR NAMED-HELPER:
MENU  = $(CYAN)
ITEM  = $(BRIGHTCYAN)
DONE  = $(CYAN)
CHECK = $(GREEN)
ERROR = $(RED)


## #################################### ##
##  R U L E S                           ##
## #################################### ##

all : dofull

## ##################### ##
##  LATEX (DVI/PS/PDF)   ##
## ##################### ##

dofull:
	$(SILENT) $(MAKE) dolatex 
	$(SILENT) # $(MAKE) domakeidx
	$(SILENT) $(MAKE) dobibtex 
	$(SILENT) $(MAKE) dolatex
	$(SILENT) $(MAKE) dolatex
	$(SILENT) $(MAKE) dodvips
	$(SILENT) $(MAKE) dops2pdf

dolatex:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- Running latex on '$(TEXFILE)'"; $(RESET)
	$(SILENT) $(LATEX) $(TEXFILE)
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

dodvips:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- Running dvips on '$(PREFIX)'"; $(RESET)
	$(SILENT) $(DVIPS) $(DVIPSFLAGS) $(PREFIX) -o$(PSFILE)
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

dops2pdf:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- Running ps2pdf on '$(PSFILE)'"; $(RESET)
	$(SILENT) $(PS2PDF) $(PS2PDFFLAGS) $(PSFILE)
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

## ##################### ##
##  PDFLATEX (PDF)       ##
## ##################### ##

dofullpdf:
	$(SILENT) $(MAKE) dopdflatex
	$(SILENT) # $(MAKE) domakeidx
	$(SILENT) $(MAKE) dobibtex
	$(SILENT) $(MAKE) dopdflatex
	$(SILENT) $(MAKE) dopdflatex

dopdflatex:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- Creating '$(PDFFILE)' via $(PDFLATEX)"; $(RESET)
	$(SILENT) $(PDFLATEX) $(TEXFILE)
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

## ##################### ##
##  GENERAL              ##
## ##################### ##

dobibtex:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- Running bibtex on '$(PREFIX)'"; $(RESET)
	$(SILENT) $(BIBTEX) $(PREFIX) 
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

domakeidx:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- Running makeindex on '$(IDXFILE)'"; $(RESET)
	$(SILENT) $(MAKEIDX) $(IDXFILE) 
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

## ##################### ##
##  CLEANING             ##
## ##################### ##

clean:
	$(SILENT) $(MENU); $(MSG) " -----------------------------------------------------------------------"; $(RESET)
	$(SILENT) $(ITEM); $(MSG) "  -- CLEANING UP ..."; $(RESET)
	$(SILENT) $(RMF) $(RMFILES)
	$(SILENT) $(DONE); $(MSG) " ------------------------------------------------------------- done. ---"; $(RESET)

# ############################################################################
#                                                                  END OF FILE
# ############################################################################

